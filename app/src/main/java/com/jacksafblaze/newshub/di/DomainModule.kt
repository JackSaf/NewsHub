package com.jacksafblaze.newshub.di

import com.jacksafblaze.newshub.domain.repository.NewsRepository
import com.jacksafblaze.newshub.domain.usecase.*
import com.jacksafblaze.newshub.domain.usecaseimpl.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {
    @Provides
    fun provideViewSearchedNewsUseCase(repository: NewsRepository): ViewSearchedNewsUseCase {
        return ViewSearchedNewsUseCaseImpl(repository)
    }

    @Provides
    fun provideViewTopHeadlinesUseCase(repository: NewsRepository): ViewTopHeadlinesUseCase {
        return ViewTopHeadlinesUseCaseImpl(repository)
    }

    @Provides
    fun provideSaveArticleUseCase(repository: NewsRepository): SaveArticleUseCase {
        return SaveArticleUseCaseImpl(repository)
    }

    @Provides
    fun provideViewSavedArticlesUseCase(repository: NewsRepository): ViewSavedArticlesUseCase {
        return ViewSavedArticlesUseCaseImpl(repository)
    }

    @Provides
    fun provideDeleteSavedArticleUseCase(repository: NewsRepository): DeleteSavedArticleUseCase {
        return DeleteSavedArticleUseCaseImpl(repository)
    }


}