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
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class LocalModule {
    @Provides
    @Singleton
    @Named(value = DB_NAME)
    fun provideDatabaseName(): String {
        return BOOKS_DB_NAME
    }

    @Provides
    @Singleton
    fun provideDatabase(
        @Named(value = DB_NAME) dbname: String,
        @ApplicationContext context: Context
    ): BooksDatabase {
        return Room.databaseBuilder(context, BooksDatabase::class.java, dbname)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideBooksDao(appDatabase: BooksDatabase): BooksDao {
        return appDatabase.booksDao()
    }

    companion object {
        private const val DB_NAME = "db_name"
        private const val BOOKS_DB_NAME = "books_db"
    }
}