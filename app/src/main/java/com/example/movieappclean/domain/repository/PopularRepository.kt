package com.example.movieappclean.domain.repository

import androidx.paging.Pager
import com.example.movieappclean.data.dataSource.remote.models.PopularResponseResult
import com.example.movieappclean.domain.entities.local.PopularLocal
import com.example.movieappclean.domain.repository.base.Repository
import kotlinx.coroutines.flow.Flow

abstract class PopularRepository : Repository() {
    abstract  fun getPopularNetwork(): Pager<Int, PopularLocal>

}