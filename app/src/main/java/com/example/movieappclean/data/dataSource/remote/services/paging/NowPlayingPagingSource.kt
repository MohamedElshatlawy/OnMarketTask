package com.example.movieappclean.data.dataSource.remote.services.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movieappclean.data.dataSource.local.dao.nowplaying.NowPlayingDAO
import com.example.movieappclean.data.dataSource.local.dao.nowplaying.NowPlayingDaoImp.insertList
import com.example.movieappclean.data.dataSource.local.dao.popular.PopularDaoImp.insertList
import com.example.movieappclean.data.dataSource.remote.models.NowPlayingResponseResult
import com.example.movieappclean.data.dataSource.remote.services.NowPlayingService
import com.example.movieappclean.domain.entities.local.NowPlayingLocal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NowPlayingPagingSource(
    private val nowPlayingService: NowPlayingService,
    private val dao: NowPlayingDAO
) : PagingSource<Int, NowPlayingLocal>() {

    override fun getRefreshKey(state: PagingState<Int, NowPlayingLocal>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NowPlayingLocal> {
        val response = nowPlayingService.getNowPlaying(
            page = params.key ?: 1
        )

        return response.toLoadState(
            onSuccess = {
                withContext(Dispatchers.IO) {
                    dao.insertList(it.results,params.key)
                    dao.getNowPlayingList(params.key)
                }
            },
            nextKey = {
                val k = if (it.page!=it.totalPages) (params.key ?: 1) + 1 else null
                k
            })
    }


}