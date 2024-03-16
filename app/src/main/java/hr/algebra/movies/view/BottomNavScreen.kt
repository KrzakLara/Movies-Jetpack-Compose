package hr.algebra.movies.view

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import hr.algebra.movies.R

sealed class BottomNavScreen ( // discuss sealed class
    val route: String, // must not be in resources!
    val title: Int, // no primitives - discuss, resources
    val icon: ImageVector
) { // now Tools -> Kotlin -> Show Kotlin Bytecode -> it is abstract class with private constructor, so only inner classes can extend! WOW TRICK!!!
    object Movies: BottomNavScreen(
        route = "movies",
        title = R.string.movies,
        icon = Icons.Default.Movie
    )
    object Map: BottomNavScreen(
        route = "map",
        title = R.string.map,
        icon = Icons.Default.Map
    )
    object About: BottomNavScreen(
        route = "about",
        title = R.string.about,
        icon = Icons.Default.Person
    ) // static inner classes -> java does not support top level static classes! // explain why static!!!

    object Settings : BottomNavScreen(
        route = "settings",
        title = R.string.settings,
        icon = Icons.Default.Settings
    )

}