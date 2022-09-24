package com.jacksafblaze.newshub.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.jacksafblaze.newshub.R
import com.jacksafblaze.newshub.databinding.FragmentNewsSearchBinding
import com.jacksafblaze.newshub.presentation.adapter.NewsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NewsSearchFragment : Fragment() {
    val viewModel: NewsSearchViewModel by viewModels()
    private lateinit var adapter: NewsAdapter
    private lateinit var binding: FragmentNewsSearchBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_news_search, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        initRecyclerView()
        viewSearchedNews()
        return binding.root
    }
    fun initRecyclerView(){
        adapter = NewsAdapter()
        binding.articleList.layoutManager = LinearLayoutManager(requireContext())
        binding.articleList.adapter = adapter
    }

    fun viewSearchedNews(){
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.query.collect{
                    viewModel.searchForNews(it).collectLatest(adapter::submitData)
                }
            }
        }
    }

}