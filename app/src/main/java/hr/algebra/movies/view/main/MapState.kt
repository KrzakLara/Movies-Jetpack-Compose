package hr.algebra.movies.view.main

import hr.algebra.movies.model.Point

data class MapState(
    val points: List<Point> = emptyList(),
    val loading: Boolean = true
)
