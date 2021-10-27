package com.example.movieappclean.domain.repository

import androidx.paging.Pager
import com.example.movieappclean.data.dataSource.remote.models.TopRatedResponseResult
import com.example.movieappclean.domain.entities.local.TopRatedLocal
import com.example.movieappclean.domain.repository.base.Repository

abstract class TopRatedRepository : Repository() {
    abstract fun getTopRated(): Pager<Int, TopRatedLocal>

}