package com.example.movieappclean.data.dataSource.local.dao.movie

import com.example.movieappclean.data.dataSource.remote.models.MovieResponse
import com.example.movieappclean.data.dataSource.remote.models.NowPlayingResponseResult
import com.example.movieappclean.data.dataSource.remote.models.PopularResponseResult

object MovieDaoImp {
    suspend fun MovieDAO.addMovie(movie: MovieResponse) {
        this.insertMovie(movie=movie.toMovieLocal())
    }
    suspend fun MovieDAO.getMovie(mov_id: String) {
        this.getMovie(mov_id=mov_id)
    }
}