package com.example.movieappclean.domain.usecases

import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.movieappclean.data.dataSource.remote.models.PopularResponseResult
import com.example.movieappclean.data.dataSource.remote.models.TopRatedResponseResult
import com.example.movieappclean.domain.entities.local.TopRatedLocal
import com.example.movieappclean.domain.repository.PopularRepository
import com.example.movieappclean.domain.repository.TopRatedRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class TopRatedUseCase @Inject constructor(
    private val repo: TopRatedRepository
) {
    fun getTopRated(scope: CoroutineScope): Flow<PagingData<TopRatedLocal>> {
        return repo.getTopRated().flow.cachedIn(scope)
    }
}
