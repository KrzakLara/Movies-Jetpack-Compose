package hr.algebra.movies.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Entity(tableName = "movies_table")
@Serializable
data class Movie(
    @PrimaryKey(autoGenerate = true)
    @Transient
    val movieId: Int = 0,
    val id: Int,
    val title: String,
    val overview: String,
    @SerialName("poster_path")
    val poster: String,
    @SerialName("release_date")
    val date: String,
    @Transient
    var liked: Boolean = false

)
