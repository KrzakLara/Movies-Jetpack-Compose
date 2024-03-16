package hr.algebra.movies.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import hr.algebra.movies.model.MovieRemoteKeys

@Dao
interface MovieRemoteKeysDao {
    @Query("SELECT * FROM movie_remote_keys_table where id=:id")
    fun getMovieRemoteKeys(id: Int) : MovieRemoteKeys

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addMovieRemoteKeys(movieRemoteKeys: List<MovieRemoteKeys>)

    @Query("DELETE FROM movie_remote_keys_table")
    fun deleteMovieRemoteKeys()
}