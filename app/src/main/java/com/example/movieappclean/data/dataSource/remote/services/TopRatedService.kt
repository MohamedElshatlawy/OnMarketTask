package com.example.movieappclean.data.dataSource.remote.services

import com.example.movieappclean.data.dataSource.remote.models.TopRatedResponse
import com.example.movieappclean.data.dataSource.remote.models.core.NetworkResponse
import com.example.movieappclean.data.dataSource.remote.services.EndPoint.topRated
import retrofit2.http.GET
import retrofit2.http.Query

interface TopRatedService {

    @GET(topRated)
    suspend fun getTopRated(
        @Query(value = "page") page: Int
    ): NetworkResponse<TopRatedResponse>
}