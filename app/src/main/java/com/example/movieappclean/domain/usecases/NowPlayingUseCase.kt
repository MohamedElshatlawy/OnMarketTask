package com.example.movieappclean.domain.usecases

import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.movieappclean.data.dataSource.remote.models.NowPlayingResponseResult
import com.example.movieappclean.domain.entities.local.NowPlayingLocal
import com.example.movieappclean.domain.repository.NowPlayingRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class NowPlayingUseCase @Inject constructor(
    private val repo: NowPlayingRepository
) {
    fun getNowPaying(scope: CoroutineScope): Flow<PagingData<NowPlayingLocal>> {
        return repo.getNowPlaying().flow.cachedIn(scope)
    }
}
