package com.example.movieappclean.data.dataSource.remote.models


import com.example.movieappclean.data.dataSource.remote.models.core.IResponse
import com.example.movieappclean.domain.entities.local.MovieLocal
import com.squareup.moshi.Json
import java.util.*

data class MovieResponse(
    @Json(name = "adult")
    val adult: Boolean? = false,
    @Json(name = "backdrop_path")
    val backdropPath: String? = "",
    @Json(name = "belongs_to_collection")
    val belongsToCollection: BelongsToCollection? = BelongsToCollection(),
    @Json(name = "budget")
    val budget: Int? = 0,
    @Json(name = "genres")
    val genres: List<Genre>? = listOf(),
    @Json(name = "homepage")
    val homepage: String? = "",
    @Json(name = "id")
    val id: Int? = 0,
    @Json(name = "imdb_id")
    val imdbId: String? = "",
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
    @Json(name = "production_companies")
    val productionCompanies: List<ProductionCompany>? = listOf(),
    @Json(name = "production_countries")
    val productionCountries: List<ProductionCountry>? = listOf(),
    @Json(name = "release_date")
    val releaseDate: String? = "",
    @Json(name = "revenue")
    val revenue: Int? = 0,
    @Json(name = "runtime")
    val runtime: Int? = 0,
    @Json(name = "spoken_languages")
    val spokenLanguages: List<SpokenLanguage>? = listOf(),
    @Json(name = "status")
    val status: String? = "",
    @Json(name = "tagline")
    val tagline: String? = "",
    @Json(name = "title")
    val title: String? = "",
    @Json(name = "video")
    val video: Boolean? = false,
    @Json(name = "vote_average")
    val voteAverage: Double? = 0.0,
    @Json(name = "vote_count")
    val voteCount: Int? = 0
) : IResponse {
    fun proCountries() = productionCountries?.joinToString(" | ") { it.name ?: "" }
    fun proCompanies() = productionCompanies?.joinToString(" | ") { it.name ?: "" }
    fun genres() = genres?.joinToString("|") { it.name ?: "" }
    fun spokenLanguages() = spokenLanguages?.joinToString("|") { it.name ?: "" }
    fun ratingStr() = String.format(locale = Locale.US, format = "%.2f", voteAverage?.toFloat())
    fun voteInt() = voteAverage?.toInt()
    fun runTimeStr() = runtime.toString()
    fun budgetStr() = "$budget $ "
    fun revenueStr() = "$revenue $ "
    fun toMovieLocal() = MovieLocal(
        adult = adult,
        backdropPath = backdropPath,
        budget = budgetStr(),
        genres =genres(),
        homepage =homepage,
        id = id,
        imdbId = imdbId,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        productionCompanies =proCompanies(),
        productionCountries = proCountries(),
        releaseDate = releaseDate,
        revenue = revenueStr(),
        runtime = runTimeStr(),
        spokenLanguages = spokenLanguages(),
        status = status,
        tagline =tagline,
        title = title,
        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount
    )


}

data class BelongsToCollection(
    @Json(name = "backdrop_path")
    val backdropPath: String? = "",
    @Json(name = "id")
    val id: Int? = 0,
    @Json(name = "name")
    val name: String? = "",
    @Json(name = "poster_path")
    val posterPath: String? = ""
)

data class Genre(
    @Json(name = "id")
    val id: Int? = 0,
    @Json(name = "name")
    val name: String? = ""
)

data class ProductionCompany(
    @Json(name = "id")
    val id: Int? = 0,
    @Json(name = "logo_path")
    val logoPath: Any? = Any(),
    @Json(name = "name")
    val name: String? = "",
    @Json(name = "origin_country")
    val originCountry: String? = ""
)

data class SpokenLanguage(
    @Json(name = "english_name")
    val englishName: String? = "",
    @Json(name = "iso_639_1")
    val iso6391: String? = "",
    @Json(name = "name")
    val name: String? = ""
)

data class ProductionCountry(
    @Json(name = "iso_3166_1")
    val iso31661: String? = "",
    @Json(name = "name")
    val name: String? = ""
)