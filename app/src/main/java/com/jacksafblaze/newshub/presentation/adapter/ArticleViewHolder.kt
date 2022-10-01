package com.jacksafblaze.newshub.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.jacksafblaze.newshub.R
import com.jacksafblaze.newshub.domain.model.Article

class ArticleViewHolder(private val view: View, private val onArticleSelected: (Article, ImageView) -> Unit): ViewHolder(view) {
    private val rootItemLayout: CardView = view.findViewById(R.id.item_root_cardview)
    private val title: TextView = view.findViewById(R.id.article_title)
    private val description: TextView = view.findViewById(R.id.article_description)
    private val articleImage: ImageView = view.findViewById(R.id.article_image)
    private val timeAgo: TextView = view.findViewById(R.id.time_ago)
    fun bind(article: Article){
        title.text = article.title
        if(article.description.isNotBlank()){
            description.text = article.description
        }
        else{
            description.visibility = View.GONE
        }
        if(article.urlToImage.isNotBlank()){
            articleImage.transitionName = article.urlToImage
            Glide.with(view).load(article.urlToImage).into(articleImage)
            rootItemLayout.setOnClickListener{
                onArticleSelected(article, articleImage)
            }
        }
        else{
            articleImage.visibility = View.GONE
        }
        timeAgo.text = TimeAgo.publishedAtToTimeAgo(article.publishedAt)
    }
    companion object{
        fun create(parent: ViewGroup, onArticleSelected: (Article, ImageView) -> Unit): ArticleViewHolder{
            val view = LayoutInflater.from(parent.context).inflate(R.layout.news_view_item, parent, false)
            return ArticleViewHolder(view, onArticleSelected)
        }
    }
}