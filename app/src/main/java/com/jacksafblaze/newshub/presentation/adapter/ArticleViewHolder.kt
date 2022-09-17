package com.jacksafblaze.newshub.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.jacksafblaze.newshub.R
import com.jacksafblaze.newshub.domain.model.Article

class ArticleViewHolder(private val view: View): ViewHolder(view) {
    private val title: TextView = view.findViewById(R.id.article_title)
    private val description: TextView = view.findViewById(R.id.article_description)
    private val articleImage: ImageView = view.findViewById(R.id.article_image)
    private val timeAgo: TextView = view.findViewById(R.id.time_ago)
    fun bind(article: Article){
        title.text = article.title
        description.text = article.description
        Glide.with(view).load(article.urlToImage).into(articleImage)
        timeAgo.text = TimeAgo.publishedAtToTimeAgo(article.publishedAt)
    }
    companion object{
        fun create(parent: ViewGroup): ArticleViewHolder{
            val view = LayoutInflater.from(parent.context).inflate(R.layout.news_view_item, parent, false)
            return ArticleViewHolder(view)
        }
    }
}