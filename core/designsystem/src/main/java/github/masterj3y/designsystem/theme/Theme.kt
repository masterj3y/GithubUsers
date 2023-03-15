package github.masterj3y.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel

private val DarkColorPalette = darkColors(
    primary = Purple200,
    primaryVariant = Purple700,
    secondary = Teal200
)

private val LightColorPalette = lightColors(
    primary = Purple500,
    primaryVariant = Purple700,
    secondary = Teal200

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

val LocalDesignSystemViewModel =
    compositionLocalOf<DesignSystemViewModel> { error("No LocalDesignSystemViewModel Provided") }

@Composable
fun GithubUsersTheme(content: @Composable () -> Unit) {

    val systemInDarkTheme: Boolean = isSystemInDarkTheme()

    val viewModel: DesignSystemViewModel = viewModel()
    val darkModeEnabled by viewModel.darkModeEnabled.collectAsState()

    val darkMode: Boolean by remember(systemInDarkTheme, darkModeEnabled) {
        mutableStateOf(
            darkModeEnabled ?: systemInDarkTheme
        )
    }

    val colors = if (darkMode) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    CompositionLocalProvider(LocalDesignSystemViewModel provides viewModel) {
        MaterialTheme(
            colors = colors,
            typography = Typography,
            shapes = Shapes,
            content = content
        )
    }
}