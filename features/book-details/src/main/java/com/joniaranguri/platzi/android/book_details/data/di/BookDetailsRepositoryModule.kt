package com.joniaranguri.platzi.android.book_details.data.di

import com.joniaranguri.platzi.android.book_details.data.repository.BookDetailsRepositoryImpl
import com.joniaranguri.platzi.android.book_details.domain.repository.BookDetailsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface BookDetailsRepositoryModule {

    @Binds
    @Singleton
    fun bindBookDetailsRepository(impl: BookDetailsRepositoryImpl): BookDetailsRepository
}