package com.example.movieappclean.di

import com.example.movieappclean.data.dataSource.remote.services.MovieService
import com.example.movieappclean.data.dataSource.remote.services.NowPlayingService
import com.example.movieappclean.data.dataSource.remote.services.PopularService
import com.example.movieappclean.data.dataSource.remote.services.TopRatedService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit
import javax.inject.Singleton
@Module
@InstallIn(ViewModelComponent::class)
object ServiceModule {
    @ViewModelScoped
    @Provides
    fun providePopularService(
        retrofit: Retrofit
    ): PopularService = retrofit.create(PopularService::class.java)

    @ViewModelScoped
    @Provides
    fun provideTopRatedService(
        retrofit: Retrofit
    ): TopRatedService = retrofit.create(TopRatedService::class.java)
    @ViewModelScoped
    @Provides
    fun provideNowPlayingService(
        retrofit: Retrofit
    ): NowPlayingService = retrofit.create(NowPlayingService::class.java)
    @ViewModelScoped
    @Provides
    fun provideMovieService(
        retrofit: Retrofit
    ): MovieService = retrofit.create(MovieService::class.java)


}