package com.example.movieappclean.data.dataSource.local.dao.nowplaying

import com.example.movieappclean.data.dataSource.remote.models.NowPlayingResponseResult

object NowPlayingDaoImp {
    suspend fun NowPlayingDAO.insertList(labList: List<NowPlayingResponseResult>?,page:Int?) {
        val list = labList?.map { item ->
          item.toNowPlayingLocal(page)
        } ?: return
        insertNowPlayingList(list = list)
    }
}