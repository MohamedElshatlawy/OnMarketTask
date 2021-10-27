package com.example.movieappclean.data.dataSource.local.dao.popular

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movieappclean.domain.entities.local.PopularLocal
import kotlinx.coroutines.flow.Flow


@Dao
interface PopularDAO {

    @Insert(entity = PopularLocal::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPopularList(list: List<PopularLocal>?)
    @Query("SELECT * FROM popular where page=:page")
    fun getAll(page:Int?): List<PopularLocal>

    @Query("DELETE FROM popular")
    suspend fun clearAll()



}