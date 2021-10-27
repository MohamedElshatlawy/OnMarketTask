package com.example.movieappclean.data.dataSource.remote.services.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movieappclean.data.dataSource.local.dao.popular.PopularDAO
import com.example.movieappclean.data.dataSource.local.dao.popular.PopularDaoImp.insertList
import com.example.movieappclean.data.dataSource.remote.models.PopularResponseResult
import com.example.movieappclean.data.dataSource.remote.services.PopularService
import com.example.movieappclean.domain.entities.local.PopularLocal
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

class PopularPagingSource(
    private val popularService: PopularService,
    private val dao: PopularDAO
) : PagingSource<Int, PopularLocal>() {

    override fun getRefreshKey(state: PagingState<Int, PopularLocal>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PopularLocal> {
        val response = popularService.getPopular(
            page = params.key ?: 1
        )

        return response.toLoadState(
            onSuccess = {
                withContext(IO){
                dao.insertList(it.results,params.key)
                dao.getAll(params.key)
                }
            },
            nextKey = {
                val k = if (it.page!=it.totalPages) (params.key ?: 1) + 1 else null
                k
            })
    }


}