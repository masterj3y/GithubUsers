package github.masterj3y.githubusers.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import github.masterj3y.favorites.navigation.favoritesScreen
import github.masterj3y.favorites.navigation.navigateToFavorites
import github.masterj3y.profile.navigation.navigateToProfile
import github.masterj3y.profile.navigation.profileScreen
import github.masterj3y.search.navigation.SEARCH_ROUT
import github.masterj3y.search.navigation.searchScreen

@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController(),
    startDestination: String = SEARCH_ROUT,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        searchScreen(
            navigateToFavorites = navController::navigateToFavorites,
            navigateToProfile = navController::navigateToProfile
        )
        profileScreen(
            onBackClick = navController::popBackStack,
            navigateToFavorites = navController::navigateToFavorites
        )
        favoritesScreen(onBackClick = navController::popBackStack)
    }
}