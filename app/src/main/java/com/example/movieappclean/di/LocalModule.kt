package com.example.movieappclean.di

import android.content.Context
import com.example.movieappclean.data.dataSource.local.AppDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {
    @Singleton
    @Provides
    fun provideDataBase(
        @ApplicationContext context: Context
    ): AppDB = AppDB.getInstance(context)
}