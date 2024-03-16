package hr.algebra.movies.repository

import hr.algebra.movies.api.PointsApi
import hr.algebra.movies.model.Point
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PointsRepository @Inject constructor(
    private val pointsApi: PointsApi
) {
    suspend fun getPoints() : List<Point> {
        return withContext(Dispatchers.IO) {
            pointsApi.getPoints().values.toList()
        }
    }
}