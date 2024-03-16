package hr.algebra.movies.db

import androidx.room.Database
import androidx.room.RoomDatabase
import hr.algebra.movies.dao.MovieDao
import hr.algebra.movies.dao.MovieRemoteKeysDao
import hr.algebra.movies.model.Movie
import hr.algebra.movies.model.MovieRemoteKeys

@Database(entities = [Movie::class, MovieRemoteKeys::class],
    version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase(){
    abstract fun movieDao(): MovieDao
    abstract fun movieRemoteKeysDao(): MovieRemoteKeysDao
}