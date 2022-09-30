package com.jacksafblaze.newshub.presentation.adapter

import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.jacksafblaze.newshub.R
import com.jacksafblaze.newshub.domain.model.Article
import com.jacksafblaze.newshub.presentation.model.UiModel

class NewsAdapter: PagingDataAdapter<UiModel, ViewHolder>(uiModelComparator) {
    lateinit var onArticleSelected: (Article, ImageView) -> Unit

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val uiModel = getItem(position)
        uiModel.let {
            when(uiModel){
                is UiModel.NewsItem -> (holder as ArticleViewHolder).bind(uiModel.article)
                is UiModel.SeparatorItem -> (holder as SeparatorViewHolder).bind(uiModel.description)
                else -> {}
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(getItem(position)){
            is UiModel.NewsItem -> R.layout.news_view_item
            is UiModel.SeparatorItem -> R.layout.separator_view_item
            else -> throw UnsupportedOperationException("Unknown view")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return if(viewType == R.layout.news_view_item){
            ArticleViewHolder.create(parent, onArticleSelected)
        }
        else{
            SeparatorViewHolder.create(parent)
        }
    }

    companion object{
        private val uiModelComparator = object: DiffUtil.ItemCallback<UiModel>(){
            override fun areItemsTheSame(oldItem: UiModel, newItem: UiModel): Boolean {
                return (oldItem is UiModel.NewsItem && newItem is UiModel.NewsItem && oldItem.article.url == newItem.article.url) ||
                        (oldItem is UiModel.SeparatorItem && newItem is UiModel.SeparatorItem && oldItem.description == newItem.description)
            }

            override fun areContentsTheSame(oldItem: UiModel, newItem: UiModel): Boolean {
                return oldItem == newItem
            }
        }
    }
}