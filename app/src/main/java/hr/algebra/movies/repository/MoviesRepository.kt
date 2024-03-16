package hr.algebra.movies.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import hr.algebra.movies.api.MoviesApi
import hr.algebra.movies.db.MovieDatabase
import hr.algebra.movies.model.Movie
import hr.algebra.movies.paging.MoviesRemoteMediator
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ExperimentalPagingApi
class MoviesRepository @Inject constructor(
    private val moviesApi: MoviesApi,
    private val movieDatabase: MovieDatabase
) {
    fun getMovies() : Flow<PagingData<Movie>> {

        val pagingSourceFactory = { movieDatabase.movieDao().getMovies() }

        return Pager(
            config = PagingConfig(pageSize = 10),
            remoteMediator = MoviesRemoteMediator(
                moviesApi,
                movieDatabase
            ),
            pagingSourceFactory = pagingSourceFactory

        ).flow

    }

    fun update(movie: Movie) = movieDatabase.movieDao().update(movie)

    fun delete(movie: Movie) = movieDatabase.movieDao().delete(movie)


}