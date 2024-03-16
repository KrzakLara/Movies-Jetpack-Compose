package hr.algebra.movies.api

import hr.algebra.movies.BuildConfig
import hr.algebra.movies.model.PageResult
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {
    @GET("popular")
    suspend fun getMovies(
        @Query("api_key") apiKey: String = BuildConfig.TMDB_KEY,
        @Query("page") page: Int = 1
    ) : PageResult
}