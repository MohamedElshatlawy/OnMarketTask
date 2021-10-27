package com.example.movieappclean.domain.repository

import androidx.paging.Pager
import com.example.movieappclean.data.dataSource.remote.models.NowPlayingResponseResult
import com.example.movieappclean.data.dataSource.remote.models.TopRatedResponseResult
import com.example.movieappclean.domain.entities.local.NowPlayingLocal
import com.example.movieappclean.domain.repository.base.Repository

abstract class NowPlayingRepository : Repository() {
    abstract fun getNowPlaying(): Pager<Int, NowPlayingLocal>

}