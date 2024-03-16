package hr.algebra.movies.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import dagger.hilt.android.lifecycle.HiltViewModel
import hr.algebra.movies.model.Movie
import hr.algebra.movies.repository.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalPagingApi
@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val repository: MoviesRepository
) : ViewModel(){
    val movies = repository.getMovies()

    fun update(movie: Movie) {
        viewModelScope.launch (Dispatchers.IO){
            repository.update(movie)
        }
    }

    fun delete(movie: Movie) {
        viewModelScope.launch (Dispatchers.IO){
            repository.delete(movie)
        }
    }
}