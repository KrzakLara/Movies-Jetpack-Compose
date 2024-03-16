import androidx.compose.material3.Typography
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsControllerCompat
import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme

// Example typography definition using Material 3
// Adjust and expand according to your design needs
private val AppTypography = Typography(
    // Define your typography customization here
)

private val DarkColorScheme = darkColorScheme(
    primary = Color.White,
    secondary = Color(0xFF388E3C), // Example green color
    tertiary = Color(0xFFD32F2F), // Example red color
    surface = Color.Black,
    // Add other color customizations for dark theme
)

private val LightColorScheme = lightColorScheme(
    primary = Color.Black,
    secondary = Color(0xFF388E3C), // Example green color
    tertiary = Color(0xFFD32F2F), // Example red color
    surface = Color.White,
    // Add other color customizations for light theme
)

@Composable
fun MoviesTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S,
    content: @Composable () -> Unit
) {
    val colors = if (dynamicColor) {
        val context = LocalContext.current
        if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
    } else {
        if (darkTheme) DarkColorScheme else LightColorScheme
    }

    MaterialTheme(
        colorScheme = colors,
        typography = AppTypography,
        content = content
    )

    // System UI color adjustment for immersive theming
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val windowInsetsController = ViewCompat.getWindowInsetsController(view)
            windowInsetsController?.isAppearanceLightStatusBars = !darkTheme
            windowInsetsController?.isAppearanceLightNavigationBars = !darkTheme
            (view.context as? Activity)?.window?.statusBarColor = colors.surface.toArgb()
            (view.context as? Activity)?.window?.navigationBarColor = colors.surface.toArgb()
        }
    }
}
