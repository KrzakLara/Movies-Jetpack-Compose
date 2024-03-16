package hr.algebra.movies.view.main

import androidx.paging.ExperimentalPagingApi
import hr.algebra.movies.viewmodel.MoviesViewModel

@ExperimentalPagingApi
class MoviesState (moviesViewModel: MoviesViewModel){
    val movies = moviesViewModel.movies
}