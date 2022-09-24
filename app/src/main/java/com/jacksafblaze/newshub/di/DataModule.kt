package com.jacksafblaze.newshub.di

import com.jacksafblaze.newshub.data.database.api.NewsDao
import com.jacksafblaze.newshub.data.network.api.RetService
import com.jacksafblaze.newshub.data.network.repository.NewsRepositoryImpl
import com.jacksafblaze.newshub.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideRepository(newsService: RetService, newsDao: NewsDao): NewsRepository{
        return NewsRepositoryImpl(newsService, newsDao)
    }
}