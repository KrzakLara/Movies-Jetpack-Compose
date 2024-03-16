package hr.algebra.movies.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import hr.algebra.movies.db.MovieDatabase
import javax.inject.Singleton

private const val MOVIE_DATABASE = "movie_database"
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ) : MovieDatabase {
        return Room.databaseBuilder(
            context,
            MovieDatabase::class.java,
            MOVIE_DATABASE
        ).build()
    }
}