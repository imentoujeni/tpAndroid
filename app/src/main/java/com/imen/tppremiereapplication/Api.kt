package com.imen.tppremiereapplication

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET("trending/movie/week")
    suspend fun lastMovies(@Query("api_key") api_key: String): Movies

    @GET("movie/{id}")
    suspend fun getMovie(@Path("id") id: Int,@Query("api_key") api_key: String): MovieDetail

    @GET("trending/tv/week")
    suspend fun lastTVShow(@Query("api_key") api_key: String): TvShows

    @GET("tv/{id}")
    suspend fun getTv(@Path("id") id: Int,@Query("api_key") api_key: String): MovieDetail

    @GET("person/popular")
    suspend fun getActors(@Query("api_key") api_key: String): Actors

    @GET("search/movie")
    suspend fun searchMovies(@Query("query") query: String, @Query("api_key") api_key: String): Movies
}