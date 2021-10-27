package com.example.movieappclean.domain.usecases

import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.movieappclean.data.dataSource.remote.models.PopularResponseResult
import com.example.movieappclean.domain.entities.local.PopularLocal
import com.example.movieappclean.domain.repository.PopularRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class PopularUseCase @Inject constructor(
    private val repo: PopularRepository
) {

    fun getPopularNetwork(scope: CoroutineScope): Flow<PagingData<PopularLocal>> {
        return repo.getPopularNetwork().flow.cachedIn(scope)
    }
}
