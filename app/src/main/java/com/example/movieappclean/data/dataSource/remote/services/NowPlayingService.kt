package com.example.movieappclean.data.dataSource.remote.services

import com.example.movieappclean.data.dataSource.remote.models.NowPlayingResponse
import com.example.movieappclean.data.dataSource.remote.models.core.NetworkResponse
import com.example.movieappclean.data.dataSource.remote.services.EndPoint.nowPlaying
import retrofit2.http.GET
import retrofit2.http.Query

interface NowPlayingService {

    @GET(nowPlaying)
    suspend fun getNowPlaying(
        @Query(value = "page") page: Int
    ): NetworkResponse<NowPlayingResponse>
}