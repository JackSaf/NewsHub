package com.jacksafblaze.newshub.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jacksafblaze.newshub.data.database.NewsDatabase
import com.jacksafblaze.newshub.data.database.api.NewsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    @Singleton
    fun provideRoomDatabase(@ApplicationContext context: Context): NewsDatabase{
        return Room.databaseBuilder(context, NewsDatabase::class.java, "NewsDatabase").build()
    }

    @Provides
    @Singleton
    fun provideNewsDao(database: NewsDatabase): NewsDao{
        return database.newsDao()
    }
}