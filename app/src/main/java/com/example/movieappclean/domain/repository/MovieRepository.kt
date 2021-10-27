package com.example.movieappclean.domain.repository

import com.example.movieappclean.data.dataSource.remote.models.MovieResponse
import com.example.movieappclean.domain.entities.MovieRequest
import com.example.movieappclean.domain.entities.common.Status
import com.example.movieappclean.domain.entities.local.MovieLocal
import com.example.movieappclean.domain.repository.base.Repository
import kotlinx.coroutines.flow.Flow

abstract class MovieRepository :Repository(){
    abstract suspend fun getMovie(prams: MovieRequest): Flow<Status<MovieLocal>>
}