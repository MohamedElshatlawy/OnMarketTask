package com.example.movieappclean.data.repositoryImp

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.movieappclean.data.constants.Constants
import com.example.movieappclean.data.dataSource.local.dao.nowplaying.NowPlayingDAO
import com.example.movieappclean.data.dataSource.local.dao.popular.PopularDAO
import com.example.movieappclean.data.dataSource.remote.services.NowPlayingService
import com.example.movieappclean.data.dataSource.remote.services.paging.NowPlayingPagingSource
import com.example.movieappclean.domain.entities.local.NowPlayingLocal
import com.example.movieappclean.domain.repository.NowPlayingRepository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class NowPlayingRepositoryImp @Inject constructor(
    private val remoteDataService: NowPlayingService,
    private val nowPlayingDAO: NowPlayingDAO
) :
    NowPlayingRepository() {
    override fun getNowPlaying(): Pager<Int, NowPlayingLocal> {
        return Pager(
            config = PagingConfig(
                pageSize = Constants.pageSize,
                enablePlaceholders = false
            )
        ) { NowPlayingPagingSource(remoteDataService,nowPlayingDAO) }
    }

}