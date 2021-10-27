package com.example.movieappclean.data.dataSource.remote.services

import com.example.movieappclean.data.dataSource.remote.models.MovieResponse
import com.example.movieappclean.data.dataSource.remote.models.core.NetworkResponse
import com.example.movieappclean.data.dataSource.remote.services.EndPoint.movie
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieService {
    @GET("$movie{movie_id}")
    suspend fun getMovie(
        @Path("movie_id") movie_id: Int
    ): NetworkResponse<MovieResponse>

}