package github.masterj3y.profile.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import github.masterj3y.profile.ui.ProfileScreen

private const val PROFILE_ROUT = "profile"
private const val USER_NAME_ARG = "user-name"

fun NavController.navigateToProfile(userName: String) {
    this.navigate("$PROFILE_ROUT/$userName")
}

fun NavGraphBuilder.profileScreen(onBackClick: () -> Unit,navigateToFavorites: () -> Unit) {
    composable(
        route = "$PROFILE_ROUT/{$USER_NAME_ARG}",
        arguments = listOf(
            navArgument(USER_NAME_ARG) { type = NavType.StringType },
        ),
    ) {
        ProfileScreen(
            requireNotNull(it.arguments?.getString(USER_NAME_ARG)),
            onBackClick = onBackClick,
            navigateToFavorites = navigateToFavorites
        )
    }
}