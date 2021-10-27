package com.example.movieappclean.data.dataSource.local.dao.nowplaying

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movieappclean.domain.entities.local.NowPlayingLocal
import kotlinx.coroutines.flow.Flow


@Dao
interface NowPlayingDAO {

    @Insert(entity = NowPlayingLocal::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNowPlayingList(list: List<NowPlayingLocal>)

    @Query("SELECT * FROM nowPlaying where page=:page")
    fun getNowPlayingList(page:Int?): List<NowPlayingLocal>


}