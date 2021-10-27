package com.example.movieappclean.data.dataSource.local.dao.movie

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movieappclean.domain.entities.local.MovieLocal
import com.example.movieappclean.domain.entities.local.NowPlayingLocal
import kotlinx.coroutines.flow.Flow


@Dao
interface MovieDAO {

    @Insert(entity = MovieLocal::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: MovieLocal)

    @Query("SELECT * FROM movie WHERE id=:mov_id ")
    fun getMovie(mov_id:String): MovieLocal


}