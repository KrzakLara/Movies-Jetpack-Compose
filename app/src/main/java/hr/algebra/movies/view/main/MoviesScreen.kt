package hr.algebra.movies.view.main

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.paging.ExperimentalPagingApi
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.AsyncImage
import coil.request.ImageRequest
import hr.algebra.movies.R
import hr.algebra.movies.di.MOVIE_IMAGES_API_URL
import hr.algebra.movies.model.Movie
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPagingApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MoviesScreen(
    modifier: Modifier = Modifier,
    moviesState: MoviesState,
    onUpdate: (Movie) -> Unit,
    onDelete: (Movie) -> Unit
) {
    val movies: LazyPagingItems<Movie> = moviesState.movies.collectAsLazyPagingItems()
    val refreshingState = rememberSwipeRefreshState(isRefreshing = false)
    var searchQuery by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                label = { Text("Search Movies") }
            )
        }
    ) {
        SwipeRefresh(
            state = refreshingState,
            onRefresh = { /* TODO: Implement refresh logic */ }
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(all = 12.dp)
            ) {
                items(
                    items = movies,
                    key = { movie -> movie.id }
                ) { movie ->
                    if (movie != null && movie.title.contains(searchQuery, ignoreCase = true)) {
                        MovieItem(
                            modifier = modifier,
                            movie = movie,
                            onUpdate = onUpdate,
                            onDelete = onDelete
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun MovieItem(
    modifier: Modifier,
    movie: Movie,
    onUpdate: (Movie) -> Unit,
    onDelete: (Movie) -> Unit
) {
    val updateAction = SwipeAction(
        onSwipe = { onUpdate(movie) },
        background = MaterialTheme.colorScheme.secondary,
        icon = {
            Icon(
                modifier = modifier.size(150.dp),
                imageVector = Icons.Default.Favorite,
                contentDescription = stringResource(R.string.like) // Localized content description
            )
        }
    )

    val deleteAction = SwipeAction(
        onSwipe = { onDelete(movie) },
        background = MaterialTheme.colorScheme.tertiary,
        icon = {
            Icon(
                modifier = modifier.size(150.dp),
                imageVector = Icons.Default.Delete,
                contentDescription = stringResource(R.string.delete)
            )
        }
    )

    SwipeableActionsBox(
        modifier = modifier,
        backgroundUntilSwipeThreshold = MaterialTheme.colorScheme.surface,
        swipeThreshold = 150.dp,
        startActions = listOf(updateAction),
        endActions = listOf(deleteAction)
    ) {
        Card(modifier = modifier.fillMaxWidth()) {
            Column(
                modifier = modifier.background(color = MaterialTheme.colorScheme.surface),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data("$MOVIE_IMAGES_API_URL${movie.poster}")
                        .crossfade(true)
                        .build(),
                    contentDescription = "${movie.title}, Movie Poster",
                    contentScale = ContentScale.FillBounds,
                    modifier = modifier
                        .height(450.dp)
                        .clip(RoundedCornerShape(20.dp))
                )
                Text(
                    text = movie.title,
                    modifier = modifier.padding(top = 6.dp),
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.tertiary
                )
                Text(
                    text = movie.overview,
                    modifier = modifier.padding(8.dp),
                    color = MaterialTheme.colorScheme.tertiary
                )
            }
        }
    }
}
