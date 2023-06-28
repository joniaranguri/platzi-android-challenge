package com.joniaranguri.platzi.android.book_details.data.di

import com.joniaranguri.platzi.android.book_details.data.repository.AuthorDetailsRepositoryImpl
import com.joniaranguri.platzi.android.book_details.data.repository.BookDetailsRepositoryImpl
import com.joniaranguri.platzi.android.book_details.domain.repository.AuthorDetailsRepository
import com.joniaranguri.platzi.android.book_details.domain.repository.BookDetailsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface AuthorDetailsRepositoryModule {

    @Binds
    @Singleton
    fun bindAuthorDetailsRepository(impl: AuthorDetailsRepositoryImpl): AuthorDetailsRepository
}