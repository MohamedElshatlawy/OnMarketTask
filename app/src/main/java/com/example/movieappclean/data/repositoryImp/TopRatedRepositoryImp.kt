package com.example.movieappclean.data.repositoryImp

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.movieappclean.data.constants.Constants
import com.example.movieappclean.data.dataSource.local.dao.toprated.TopRatedDAO
import com.example.movieappclean.data.dataSource.remote.services.TopRatedService
import com.example.movieappclean.data.dataSource.remote.services.paging.TopRatedPagingSource
import com.example.movieappclean.domain.entities.local.TopRatedLocal
import com.example.movieappclean.domain.repository.TopRatedRepository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class TopRatedRepositoryImp @Inject constructor(
    private val remoteDataService: TopRatedService,
    private val dao: TopRatedDAO
) :
    TopRatedRepository() {
    override fun getTopRated(): Pager<Int, TopRatedLocal> {
        return Pager(
            config = PagingConfig(
                pageSize = Constants.pageSize,
                enablePlaceholders = false
            )
        ) { TopRatedPagingSource(remoteDataService, dao) }
    }

}