package com.example.movieappclean.domain.usecases

import com.example.movieappclean.data.dataSource.remote.models.MovieResponse
import com.example.movieappclean.domain.entities.MovieRequest
import com.example.movieappclean.domain.entities.common.Status
import com.example.movieappclean.domain.entities.local.MovieLocal
import com.example.movieappclean.domain.repository.MovieRepository
import com.example.movieappclean.domain.usecases.core.UseCase
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


@ViewModelScoped
class MovieUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) : UseCase<MovieRequest, MovieLocal>() {
    override suspend fun run(param: MovieRequest): Flow<Status<MovieLocal>> {
        return movieRepository.getMovie(param)
    }
}