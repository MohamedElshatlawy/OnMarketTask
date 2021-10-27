package com.example.movieappclean.data.repositoryImp


import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.movieappclean.data.constants.Constants.pageSize
import com.example.movieappclean.data.dataSource.local.dao.popular.PopularDAO
import com.example.movieappclean.data.dataSource.remote.services.PopularService
import com.example.movieappclean.data.dataSource.remote.services.paging.PopularPagingSource
import com.example.movieappclean.domain.entities.local.PopularLocal
import com.example.movieappclean.domain.repository.PopularRepository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject
@ViewModelScoped
class PopularRepositoryImp @Inject constructor(
    private val remoteDataService: PopularService,
    private val popularDao: PopularDAO
) : PopularRepository() {
        override  fun getPopularNetwork(): Pager<Int, PopularLocal> {
        return Pager(
            config = PagingConfig(
                pageSize = pageSize,
                enablePlaceholders = false
            )){ PopularPagingSource(remoteDataService,popularDao) }
    }



}