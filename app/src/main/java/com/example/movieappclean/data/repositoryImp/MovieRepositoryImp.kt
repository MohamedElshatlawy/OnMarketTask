package com.example.movieappclean.data.repositoryImp

import com.example.movieappclean.data.dataSource.local.dao.movie.MovieDAO
import com.example.movieappclean.data.dataSource.local.dao.movie.MovieDaoImp.addMovie
import com.example.movieappclean.data.dataSource.remote.models.MovieResponse
import com.example.movieappclean.data.dataSource.remote.services.MovieService
import com.example.movieappclean.domain.entities.MovieRequest
import com.example.movieappclean.domain.entities.common.Status
import com.example.movieappclean.domain.entities.local.MovieLocal
import com.example.movieappclean.domain.repository.MovieRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject
@ViewModelScoped
class MovieRepositoryImp @Inject constructor(
    private val remoteDataService:MovieService,
    private val dao:MovieDAO
):MovieRepository() {
    override suspend fun getMovie(prams: MovieRequest): Flow<Status<MovieLocal>> {
        return fetchRemoteData(
            response = {
                remoteDataService.getMovie(movie_id = prams.movieID)
            },
            onSuccess = {
                    dao.addMovie(it)
                    dao.getMovie(it.id.toString())
            },
            context = IO
        )
    }

}