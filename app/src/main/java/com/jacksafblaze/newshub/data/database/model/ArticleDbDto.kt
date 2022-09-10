package com.jacksafblaze.newshub.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jacksafblaze.newshub.domain.model.Source

@Entity(tableName = "articles")
data class ArticleDbDto(@PrimaryKey(autoGenerate = true) val uid: Int,
                        val author: String?,
                        val content: String?,
                        val description: String?,
                        val publishedAt: String?,
                        val source: Source?,
                        val title: String?,
                        val url: String?,
                        val urlToImage: String?)