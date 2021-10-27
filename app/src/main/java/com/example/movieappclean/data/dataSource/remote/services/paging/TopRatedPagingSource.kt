package com.example.movieappclean.data.dataSource.remote.services.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movieappclean.data.dataSource.local.dao.nowplaying.NowPlayingDaoImp.insertList
import com.example.movieappclean.data.dataSource.local.dao.toprated.TopRatedDAO
import com.example.movieappclean.data.dataSource.local.dao.toprated.TopRatedDaoImp.insertList
import com.example.movieappclean.data.dataSource.remote.models.TopRatedResponseResult
import com.example.movieappclean.data.dataSource.remote.services.TopRatedService
import com.example.movieappclean.domain.entities.local.TopRatedLocal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TopRatedPagingSource(
    private val topRatedService: TopRatedService,
    private val dao:TopRatedDAO
) : PagingSource<Int, TopRatedLocal>() {

    override fun getRefreshKey(state: PagingState<Int, TopRatedLocal>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TopRatedLocal> {
        val response = topRatedService.getTopRated(
            page = params.key ?: 1
        )

        return response.toLoadState(
            onSuccess = {
                withContext(Dispatchers.IO) {
                    dao.insertList(it.results,params.key)
                    dao.getTopRatedList(params.key)
                }
            },
            nextKey = {
                val k = if (it.page!=it.totalPages) (params.key ?: 1) + 1 else null
                k
            })
    }


}