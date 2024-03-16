package hr.algebra.movies.view.nav

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.paging.ExperimentalPagingApi
import hr.algebra.movies.view.BottomNavScreen
import hr.algebra.movies.view.SettingsScreen
import hr.algebra.movies.view.main.AboutScreen
import hr.algebra.movies.view.main.MapScreen
import hr.algebra.movies.view.main.MoviesScreen
import hr.algebra.movies.view.main.MoviesState
import hr.algebra.movies.viewmodel.MapViewModel
import hr.algebra.movies.viewmodel.MoviesViewModel

@ExperimentalPagingApi
@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomNavScreen.Movies.route
    ) {
        composable(route = BottomNavScreen.Movies.route) {
            val moviesViewModel = hiltViewModel<MoviesViewModel>()
            MoviesScreen(
                moviesState = MoviesState(moviesViewModel),
                onUpdate = { moviesViewModel.update(it.copy(liked = !it.liked)) },
                onDelete = { moviesViewModel.delete(it) }
            )
        }
        composable(route = BottomNavScreen.Map.route) {
            val mapViewModel = hiltViewModel<MapViewModel>()
            MapScreen(
                mapState = mapViewModel.mapState.value
            )
        }
        composable(route = BottomNavScreen.About.route) {
            AboutScreen()
        }
        composable(route = BottomNavScreen.Settings.route) {
            SettingsScreen()
        }
    }
}
