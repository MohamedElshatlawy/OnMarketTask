package com.example.movieappclean.data.dataSource.local.dao.toprated

import com.example.movieappclean.data.dataSource.remote.models.TopRatedResponseResult

object TopRatedDaoImp {
    suspend fun TopRatedDAO.insertList(labList: List<TopRatedResponseResult>?,page:Int?) {
        val list = labList?.map { item ->
            item.toTopRatedLocal(page)
        } ?: return
        insertTopRatedList(list = list)
    }
}