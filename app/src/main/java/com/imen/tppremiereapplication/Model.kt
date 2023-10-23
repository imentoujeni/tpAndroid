package com.imen.tppremiereapplication

/**
 * Movies data class
 */
data class Movies(
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)

/**
 * Movie model
 */
data class Movie (
    val adult: Boolean,
    val backdrop_path: String,
    val genre_ids: List<Int>,
    val id: Int,
    val media_type: String,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
)

/**
 * TvShows data class
 */
data class TvShows(
    val page: Int,
    val results: List<TvShow>,
    val total_pages: Int,
    val total_results: Int
)

/**
 * TvShow Model
 */
data class TvShow(
    val adult: Boolean,
    val backdrop_path: String,
    val genre_ids: List<Int>,
    val id: Int,
    val media_type: String,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val first_air_date: String,
    val name: String,
    val vote_average: Double,
    val vote_count: Int,
    val origin_country: List<String>
)

/**
 * Actors data class
 */
data class Actors(
    val page: Int,
    val results: List<Actor>,
    val total_pages: Int,
    val total_results: Int
)

/**
 * Actor model
 */
data class  Actor (
    val adult: Boolean,
    val gender: Int,
    val id: Int,
    val known_for: List<Movie>,
    val known_for_department: String,
    val name: String,
    val popularity: Float,
    val profile_path: String
)

/**
 * Movie details data Class
 */
data class MovieDetail(
    val adult: Boolean = false,
    val backdrop_path: String = "",
    val belongs_to_collection: Any = "",
    val budget: Int = 0,
    val genres: List<Genre> = listOf(),
    val homepage: String = "",
    val id: Int = 0,
    val imdb_id: String = "",
    val original_language: String = "",
    val original_title: String = "",
    val overview: String = "",
    val popularity: Double = 0.0,
    val poster_path: Any = "",
    val production_companies: List<ProductionCompany> = listOf(),
    val production_countries: List<ProductionCountry> = listOf(),
    val release_date: String = "",
    val revenue: Int = 0,
    val runtime: Int = 0,
    val spoken_languages: List<SpokenLanguage> = listOf(),
    val status: String = "",
    val tagline: String = "",
    val title: String = "",
    val video: Boolean = false,
    val vote_average: Double = 0.0,
    val vote_count: Int = 0,
    val credits: Credits = Credits()

)

/**
 * Genres data Class
 */
data class Genre(
    val id: Int,
    val name: String
)

/**
 * ProductionCountry data Class
 */
data class ProductionCompany(
    val id: Int,
    val logo_path: Any,
    val name: String,
    val origin_country: String
)

/**
 * ProductionCountry data Class
 */
data class ProductionCountry(
    val iso_3166_1: String,
    val name: String
)

/**
 * SpokenLanguage data Class
 */
data class SpokenLanguage(
    val english_name: String,
    val iso_639_1: String,
    val name: String
)

/**
 *Credits data Class
 */
data class Credits(
    val cast: List<Cast> = listOf(),
 )

/**
 * Cast data Class
 */
data class Cast(
    val adult: Boolean,
    val cast_id: Int,
    val character: String,
    val credit_id: String,
    val gender: Int,
    val id: Int,
    val known_for_department: String,
    val name: String,
    val order: Int,
    val original_name: String,
    val popularity: Double,
    val profile_path: String
)