package com.joniaranguri.platzi.android.books.list.data.di

import com.joniaranguri.platzi.android.books.list.data.repository.BooksRepositoryImpl
import com.joniaranguri.platzi.android.books.list.domain.repository.BooksRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface BooksRepositoryModule {

    @Binds
    @Singleton
    fun bindBooksRepository(impl: BooksRepositoryImpl): BooksRepository
}