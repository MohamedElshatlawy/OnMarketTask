package com.example.movieappclean.di

import com.example.movieappclean.data.dataSource.local.AppDB
import com.example.movieappclean.data.dataSource.local.dao.movie.MovieDAO
import com.example.movieappclean.data.dataSource.local.dao.nowplaying.NowPlayingDAO
import com.example.movieappclean.data.dataSource.local.dao.popular.PopularDAO
import com.example.movieappclean.data.dataSource.local.dao.toprated.TopRatedDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object LocalProviderModule {
    @Provides
    @ViewModelScoped
    fun providePopularDao(database: AppDB): PopularDAO = database.popularDao()

    @Provides
    @ViewModelScoped
    fun provideTopRatedDao(database: AppDB): TopRatedDAO = database.topRatedDao()

    @Provides
    @ViewModelScoped
    fun provideNowPlayingDao(database: AppDB): NowPlayingDAO = database.nowPlayingDao()

    @Provides
    @ViewModelScoped
    fun provideMovieDao(database: AppDB): MovieDAO = database.movieDao()


}