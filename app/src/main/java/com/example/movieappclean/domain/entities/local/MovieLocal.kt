package com.example.movieappclean.domain.entities.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


@Entity(tableName = "movie")
data class MovieLocal(

    val adult: Boolean? = false,
    val backdropPath: String? = "",
    val budget: String? = "0",
    val genres:  String? = "",
    val homepage: String? = "",
    @PrimaryKey
    val id: Int? = 0,
    val imdbId: String? = "",
    val originalLanguage: String? = "",
    val originalTitle: String? = "",
    val overview: String? = "",
    val popularity: Double? = 0.0,
    val posterPath: String? = "",
    val productionCompanies:  String? = "",
    val productionCountries:  String? = "",
    val releaseDate: String? = "",
    val revenue: String? = "0",
    val runtime: String? = "0",
    val spokenLanguages: String? = "",
    val status: String? = "",
    val tagline: String? = "",
    val title: String? = "",
    val video: Boolean? = false,
    val voteAverage: Double? = 0.0,
    val voteCount: Int? = 0
){
    fun ratingStr() = String.format(locale = Locale.US, format = "%.2f", voteAverage?.toFloat())
    fun voteInt() = voteAverage?.toInt()

}
