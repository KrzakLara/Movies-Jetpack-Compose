package hr.algebra.movies.view.nav

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.paging.ExperimentalPagingApi
import hr.algebra.movies.view.BottomNavScreen
import hr.algebra.movies.view.MainScreen
import hr.algebra.movies.view.SettingsScreen
import hr.algebra.movies.viewmodel.AuthenticationViewModel

@ExperimentalPagingApi
@Composable
fun RootNavGraph(navController: NavHostController) {
    val authenticationViewModel = viewModel<AuthenticationViewModel>()
    val context = LocalContext.current
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.AUTH) {

        authNavGraph(
            navController = navController,
            authenticationViewModel = authenticationViewModel,
            context = context)

        composable(route = Graph.MAIN) {
            MainScreen()
        }
        composable(route = BottomNavScreen.Settings.route) {
            SettingsScreen()
        }
        composable(route = BottomNavScreen.Settings.route) {
            SettingsScreen()
        }
    }

}


