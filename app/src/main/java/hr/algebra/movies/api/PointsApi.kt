package hr.algebra.movies.api

import hr.algebra.movies.model.Point
import retrofit2.http.GET

interface PointsApi {
    @GET("/points.json")
    suspend fun getPoints() : Map<String, Point>
}