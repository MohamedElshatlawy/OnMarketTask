package com.example.movieappclean.di

import com.example.movieappclean.data.repositoryImp.MovieRepositoryImp
import com.example.movieappclean.data.repositoryImp.NowPlayingRepositoryImp
import com.example.movieappclean.data.repositoryImp.PopularRepositoryImp
import com.example.movieappclean.data.repositoryImp.TopRatedRepositoryImp
import com.example.movieappclean.domain.repository.MovieRepository
import com.example.movieappclean.domain.repository.NowPlayingRepository
import com.example.movieappclean.domain.repository.PopularRepository
import com.example.movieappclean.domain.repository.TopRatedRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
@InstallIn(ViewModelComponent::class)
@Module
interface RepoModuleVMScoped {
    @ViewModelScoped
    @Binds
    fun bindPopularRepository(popularRepositoryImp: PopularRepositoryImp): PopularRepository
    @ViewModelScoped
    @Binds
    fun bindTopRatedRepository(topRatedRepositoryImp: TopRatedRepositoryImp): TopRatedRepository
    @ViewModelScoped
    @Binds
    fun bindNowPlayingRepository(nowPlayingRepositoryImp: NowPlayingRepositoryImp): NowPlayingRepository

  @ViewModelScoped
    @Binds
    fun bindMovieRepository(movieRepositoryImp: MovieRepositoryImp): MovieRepository


}

