package com.jacksafblaze.newshub.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
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
        initRecyclerView()
        viewTopHeadlines()
        setSearchView()
    }

    fun initRecyclerView(){
        adapter = NewsAdapter()
        binding.articleList.layoutManager = LinearLayoutManager(requireContext())
        binding.articleList.adapter = adapter
    }

    fun setSearchView(){
        binding.query.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
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