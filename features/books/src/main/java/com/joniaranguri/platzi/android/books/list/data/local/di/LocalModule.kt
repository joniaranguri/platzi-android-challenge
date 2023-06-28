package com.joniaranguri.platzi.android.books.list.data.local.di

import android.content.Context
import androidx.room.Room
import com.joniaranguri.platzi.android.books.list.data.local.dao.BooksDao
import com.joniaranguri.platzi.android.books.list.data.local.db.BooksDatabase
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
    ): BooksDatabase {
        return Room.databaseBuilder(context, BooksDatabase::class.java, BOOKS_DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideBooksDao(appDatabase: BooksDatabase): BooksDao {
        return appDatabase.booksDao()
    }

    companion object {
        private const val BOOKS_DB_NAME = "books_db"
    }
}