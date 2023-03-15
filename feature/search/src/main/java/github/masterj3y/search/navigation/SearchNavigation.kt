package github.masterj3y.search.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import github.masterj3y.search.ui.SearchScreen

const val SEARCH_ROUT = "search"

fun NavGraphBuilder.searchScreen(
    navigateToFavorites: () -> Unit,
    navigateToProfile: (userName: String) -> Unit,
) {
    composable(
        route = SEARCH_ROUT,
    ) {
        SearchScreen(
            navigateToFavorites = navigateToFavorites,
            navigateToProfile = navigateToProfile
        )
    }
}