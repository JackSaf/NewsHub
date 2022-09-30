package com.jacksafblaze.newshub.presentation

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.jacksafblaze.newshub.R
import com.jacksafblaze.newshub.databinding.FragmentNewsSearchBinding
import com.jacksafblaze.newshub.presentation.adapter.NewsAdapter
import com.jacksafblaze.newshub.presentation.viewmodel.NewsSearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NewsSearchFragment : Fragment() {
    val viewModel: NewsSearchViewModel by viewModels()
    private lateinit var adapter: NewsAdapter
    private var _binding: FragmentNewsSearchBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareMenu()
        initRecyclerView()
        viewTopHeadlines()
    }

    fun prepareMenu(){
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object: MenuProvider{
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.search_fragment_toolbar_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when(menuItem.itemId){
                    R.id.search_item ->{
                        val searchView = menuItem.actionView as SearchView
                        initSearchView(searchView)
                        true
                    }
                    else -> false
                }
            }

        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    fun initRecyclerView(){
        adapter = NewsAdapter()
        binding.articleList.layoutManager = LinearLayoutManager(requireContext())
        binding.articleList.adapter = adapter
    }

    fun initSearchView(searchView: SearchView){
        searchView.queryHint = getString(R.string.search)
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                if(query.isNullOrEmpty()){
                    viewTopHeadlines()
                }
                else{
                    viewModel._query.update { query }
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if(newText.isNullOrEmpty()){
                    viewTopHeadlines()
                }
                else{
                    viewSearchedNews()
                    viewModel._query.update { newText }
                }
                return true
            }

        })
    }

    fun viewTopHeadlines(){
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                    viewModel.getTopHeadlines().collectLatest(adapter::submitData)
            }
        }
    }

    fun viewSearchedNews(){
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.query.collect{
                    Log.i("collection", "Im collected")
                    viewModel.searchForNews(it).collectLatest(adapter::submitData)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}