package com.jacksafblaze.newshub.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "articles")
data class ArticleDbDto(@PrimaryKey(autoGenerate = true) val id: Int,
                        val author: String?,
                        val content: String?,
                        val description: String?,
                        @ColumnInfo(name = "published_at") val publishedAt: String?,
                        @Embedded val source: SourceDbDto?,
                        val title: String?,
                        val url: String?,
                        @ColumnInfo(name = "url_to_image") val urlToImage: String?)