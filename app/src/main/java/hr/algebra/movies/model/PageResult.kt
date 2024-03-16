package hr.algebra.movies.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PageResult(
    @SerialName("results")
    val movies: List<Movie>
)
