package com.example.movieappclean.data.dataSource.remote.models


import com.example.movieappclean.data.dataSource.remote.models.core.IResponse
import com.example.movieappclean.domain.entities.local.TopRatedLocal
import com.squareup.moshi.Json
import java.util.*

data class TopRatedResponse(
    @Json(name = "page")
    val page: Int? = 0,
    @Json(name = "results")
    val results: List<TopRatedResponseResult>? = listOf(),
    @Json(name = "total_pages")
    val totalPages: Int? = 0,
    @Json(name = "total_results")
    val totalResults: Int? = 0
) : IResponse

data class TopRatedResponseResult(
    @Json(name = "adult")
    val adult: Boolean? = false,
    @Json(name = "backdrop_path")
    val backdropPath: String? = "",
    @Json(name = "genre_ids")
    val genreIds: List<Int>? = listOf(),
    @Json(name = "id")
    val id: Int? = 0,
    @Json(name = "original_language")
    val originalLanguage: String? = "",
    @Json(name = "original_title")
    val originalTitle: String? = "",
    @Json(name = "overview")
    val overview: String? = "",
    @Json(name = "popularity")
    val popularity: Double? = 0.0,
    @Json(name = "poster_path")
    val posterPath: String? = "",
    @Json(name = "release_date")
    val releaseDate: String? = "",
    @Json(name = "title")
    val title: String? = "",
    @Json(name = "video")
    val video: Boolean? = false,
    @Json(name = "vote_average")
    val voteAverage: Double? = 0.0,
    @Json(name = "vote_count")
    val voteCount: Int? = 0
) {
    fun voteInt() = voteAverage?.toInt()
     fun ratingStr()=String.format(locale=Locale.US,format="%.2f",voteAverage?.toFloat())
    fun toTopRatedLocal(page: Int?) = TopRatedLocal(
        adult = adult,
        backdropPath = backdropPath,
        id = id,
        originalLanguage = originalLanguage ,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath =posterPath,
        releaseDate = releaseDate,
        title = title,
        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount,
        page=page
    )
}