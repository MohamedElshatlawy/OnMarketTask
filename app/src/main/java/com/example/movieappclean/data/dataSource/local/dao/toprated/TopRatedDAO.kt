package com.example.movieappclean.data.dataSource.local.dao.toprated

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movieappclean.domain.entities.local.TopRatedLocal
import kotlinx.coroutines.flow.Flow


@Dao
interface TopRatedDAO {

    @Insert(entity = TopRatedLocal::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTopRatedList(list: List<TopRatedLocal>)

    @Query("SELECT * FROM toprated where page=:page")
    fun getTopRatedList(page:Int?): List<TopRatedLocal>


}