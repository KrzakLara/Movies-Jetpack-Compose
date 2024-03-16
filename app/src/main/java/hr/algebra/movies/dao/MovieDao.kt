package hr.algebra.movies.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import hr.algebra.movies.model.Movie

@Dao
interface MovieDao {
    @Query("SELECT * FROM movies_table")
    fun getMovies() : PagingSource<Int, Movie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addMovies(movies: List<Movie>)

    @Query("DELETE FROM movies_table")
    fun deleteMovies()

    @Update
    fun update(movie: Movie)

    @Delete
    fun delete(movie: Movie)

}