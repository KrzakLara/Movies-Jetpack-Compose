package hr.algebra.movies.view

sealed class AuthNavScreen(val route: String) {
    object Login : AuthNavScreen(route = "login")
    object Register : AuthNavScreen(route = "register")
}
