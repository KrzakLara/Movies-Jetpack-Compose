package hr.algebra.movies.view

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.*
import hr.algebra.movies.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen() {
    var isFeatureEnabled by remember { mutableStateOf(false) }
    var volume by remember { mutableStateOf(0.5f) }
    var starRating by remember { mutableStateOf(0) } // Star rating state
    val context = LocalContext.current

    LazyColumn(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        item {
            Text(text = "Settings", style = MaterialTheme.typography.displayMedium)
        }

        item {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "Enable Feature")
                Spacer(modifier = Modifier.width(16.dp))
                Switch(
                    checked = isFeatureEnabled,
                    onCheckedChange = { isFeatureEnabled = it }
                )
            }
        }

        item {
            Text(text = "Volume Control")
            Slider(
                value = volume,
                onValueChange = { volume = it },
                valueRange = 0f..1f
            )
            Text(text = "Volume: ${(volume * 100).toInt()}%")
        }

        item {
            Button(
                onClick = {
                    val appPackageName = context.packageName
                    try {
                        context.startActivity(
                            Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("market://details?id=$appPackageName")
                            )
                        )
                    } catch (e: Exception) {
                        context.startActivity(
                            Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("https://play.google.com/store/apps/details?id=$appPackageName")
                            )
                        )
                    }
                },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text("Rate the App")
            }
        }

        item {
            Row(modifier = Modifier.padding(top = 8.dp)) {
                (1..5).forEach { index ->
                    Icon(
                        imageVector = if (index <= starRating) Icons.Filled.Star else Icons.Filled.StarBorder,
                        contentDescription = "Rate $index",
                        modifier = Modifier
                            .size(24.dp)
                            .clickable {
                                starRating = index
                            }
                    )
                }
            }
        }

        item {
            // Display Lottie animation if feature is enabled
            if (isFeatureEnabled) {
                val composition by rememberLottieComposition(
                    spec = LottieCompositionSpec.RawRes(R.raw.movie_settings)
                )
                LottieAnimation(
                    composition = composition,
                    modifier = Modifier.size(500.dp)
                )
            }
        }
    }
}
