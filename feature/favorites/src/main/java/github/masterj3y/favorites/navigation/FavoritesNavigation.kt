package github.masterj3y.favorites.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import github.masterj3y.favorites.ui.FavoritesScreen

private const val FAVORITES_ROUTE = "favorites"

fun NavController.navigateToFavorites() {
    this.navigate(FAVORITES_ROUTE)
}

fun NavGraphBuilder.favoritesScreen(onBackClick: () -> Unit) {
    composable(
        route = FAVORITES_ROUTE
    ) {
        FavoritesScreen(
            onBackClick = onBackClick
        )
    }
}