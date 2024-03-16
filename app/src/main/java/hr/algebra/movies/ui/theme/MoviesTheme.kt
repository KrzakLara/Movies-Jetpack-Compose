package hr.algebra.movies.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import android.os.Build
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = Color.White,
    secondary = Color(0xFF388E3C),
    tertiary = Color(0xFFD32F2F),
    surface = Color.Black,
    // Add other color customizations for the dark theme
)

private val LightColorScheme = lightColorScheme(
    primary = Color.Black,
    secondary = Color(0xFF388E3C),
    tertiary = Color(0xFFD32F2F),
    surface = Color.White,
    // Add other color customizations for the light theme
)

@Composable
fun MoviesTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S,
    content: @Composable () -> Unit
) {
    val context = LocalContext.current
    val colorScheme = when {
        dynamicColor -> if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        else -> if (darkTheme) DarkColorScheme else LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography, // Ensure you have defined your Typography
        content = content
    )
}
