package com.example.movieappclean.data.dataSource.remote.services

import com.example.movieappclean.data.dataSource.remote.models.PopularResponse
import com.example.movieappclean.data.dataSource.remote.models.core.NetworkResponse
import com.example.movieappclean.data.dataSource.remote.services.EndPoint.popular
import retrofit2.http.GET
import retrofit2.http.Query

interface PopularService {

    @GET(popular)
    suspend fun getPopular(
        @Query(value = "page") page: Int
    ): NetworkResponse<PopularResponse>
}