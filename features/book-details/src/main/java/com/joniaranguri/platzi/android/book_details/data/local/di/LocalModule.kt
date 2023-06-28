package com.joniaranguri.platzi.android.book_details.data.local.di

import android.content.Context
import androidx.room.Room
import com.joniaranguri.platzi.android.book_details.data.local.dao.AuthorDetailsDao
import com.joniaranguri.platzi.android.book_details.data.local.dao.BookDetailsDao
import com.joniaranguri.platzi.android.book_details.data.local.db.BookDetailsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class LocalModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): BookDetailsDatabase {
        return Room.databaseBuilder(context, BookDetailsDatabase::class.java, BOOK_DETAILS_DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideBookDetailsDao(appDatabase: BookDetailsDatabase): BookDetailsDao {
        return appDatabase.bookDetailsDao()
    }

    @Provides
    @Singleton
    fun provideAuthorDetailsDao(appDatabase: BookDetailsDatabase): AuthorDetailsDao {
        return appDatabase.authorDetailsDao()
    }

    companion object {
        private const val BOOK_DETAILS_DB_NAME = "book_details_db"
    }
}