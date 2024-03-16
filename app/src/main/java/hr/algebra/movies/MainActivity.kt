package hr.algebra.movies

import MoviesTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import hr.algebra.movies.viewmodel.ThemeViewModel
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.ExperimentalPagingApi
import hr.algebra.movies.view.nav.RootNavGraph

@ExperimentalPagingApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            hr.algebra.movies.ui.theme.MoviesTheme {
                Surface {
                    RootNavGraph(navController = rememberNavController())
                }
            }
        }
    }

}