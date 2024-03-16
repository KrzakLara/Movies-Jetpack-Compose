package hr.algebra.movies.model

import com.google.android.gms.maps.model.LatLng
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Point(
    val title: String,
    val address: String,
    val lat: Double,
    val lng: Double,
    val image: String
) {
    fun latLng() = LatLng(lat, lng)
}
