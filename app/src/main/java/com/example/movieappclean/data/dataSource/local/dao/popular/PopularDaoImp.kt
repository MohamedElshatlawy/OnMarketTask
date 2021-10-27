package com.example.movieappclean.data.dataSource.local.dao.popular

import com.example.movieappclean.data.dataSource.remote.models.PopularResponseResult

object PopularDaoImp {
    suspend fun PopularDAO.insertList(labList: List<PopularResponseResult>?,page:Int?) {
        val list = labList?.map { item ->
          item.toPopularLocal(page)
        } ?: return
        insertPopularList(list = list)
    }
}