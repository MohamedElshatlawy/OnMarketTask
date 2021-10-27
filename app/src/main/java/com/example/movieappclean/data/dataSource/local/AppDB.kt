package com.example.movieappclean.data.dataSource.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.movieappclean.BuildConfig
import com.example.movieappclean.data.dataSource.local.dao.movie.MovieDAO
import com.example.movieappclean.data.dataSource.local.dao.nowplaying.NowPlayingDAO
import com.example.movieappclean.data.dataSource.local.dao.popular.PopularDAO
import com.example.movieappclean.data.dataSource.local.dao.toprated.TopRatedDAO
import com.example.movieappclean.domain.entities.local.*

@Database(
    entities = [
        PopularLocal::class,
        TopRatedLocal::class,
        NowPlayingLocal::class,
        MovieLocal::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDB : RoomDatabase() {

    abstract fun popularDao(): PopularDAO
    abstract fun nowPlayingDao(): NowPlayingDAO
    abstract fun topRatedDao(): TopRatedDAO
    abstract fun movieDao(): MovieDAO


    companion object {
        @Volatile
        private var INSTANCE: AppDB? = null

        @JvmStatic
        fun getInstance(
            context: Context
        ): AppDB {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context,
                        AppDB::class.java,
                        "${BuildConfig.APPLICATION_ID}.local.database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                    instance
                }
            }
        }
    }
}