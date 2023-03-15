package github.masterj3y.profile.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import github.masterj3y.data.extension.compact
import github.masterj3y.data.model.UserProfile
import github.masterj3y.designsystem.components.*

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProfileScreen(
    userName: String,
    viewModel: ProfileViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    navigateToFavorites: () -> Unit
) {

    val userProfile by viewModel.userProfile.collectAsState()
    val followers by viewModel.followers.collectAsState()
    val following by viewModel.following.collectAsState()
    val isUserProfileSaved by viewModel.isUserProfileSaved.collectAsState()

    val (selectedTab, selectTab) = remember { mutableStateOf(Tab.Followers) }

    LaunchedEffect(userName) {
        viewModel.loadUserProfile(userName)
    }

    Scaffold(
        floatingActionButton = {
            if (isUserProfileSaved != null && userProfile != null)
                FloatingActionButton(
                    onClick = {
                        if (isUserProfileSaved == true)
                            viewModel.deleteUserProfile(userProfile!!)
                        else
                            viewModel.saveUserProfile(userProfile!!)
                    }
                ) {
                    Icon(
                        imageVector = if (isUserProfileSaved == true) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = null,
                        tint = Color.Red
                    )
                }
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {

            ProfileTopAppBar(
                title = "$userName's Profile",
                onBackClick = onBackClick,
                navigateToFavorites = navigateToFavorites
            )

            LazyColumn {

                item { ProfileHeader(userProfile = userProfile) }

                if (userProfile != null)
                    item {
                        ProfileFFR(userProfile = userProfile!!)
                    }

                stickyHeader { Tabs(selectedTab = selectedTab, onTabSelect = selectTab) }

                items(
                    if (selectedTab == Tab.Followers) followers ?: listOf() else following
                        ?: listOf()
                ) { user ->
                    SimpleUserRow(
                        modifier = Modifier
                            .fillMaxWidth(),
                        profilePicUrl = user.avatarUrl,
                        userName = user.login
                    )
                }
            }
        }
    }
}

@Composable
private fun ProfileTopAppBar(
    modifier: Modifier = Modifier,
    title: String,
    onBackClick: () -> Unit,
    navigateToFavorites: () -> Unit
) {

    AppToolbar(
        modifier = modifier,
        title = title,
        backButton = {
            ClickableIcon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null,
                onClick = onBackClick
            )
        },
        navigateToFavoriteUsers = navigateToFavorites
    )

}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun ProfileHeader(userProfile: UserProfile?) {
    Surface(color = MaterialTheme.colors.primary) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            GlideImage(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop,
                model = userProfile?.avatarUrl,
                contentDescription = null
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(text = userProfile?.login ?: "Loading", style = MaterialTheme.typography.h5)

            userProfile?.bio?.let {
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = it)
            }

        }
    }
}

@Composable
private fun ProfileFFR(userProfile: UserProfile) {
    FFR(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.primary)
            .padding(horizontal = 16.dp)
            .padding(top = 8.dp, bottom = 16.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colors.surface)
            .padding(horizontal = 8.dp, vertical = 16.dp),
        followers = userProfile.followers.compact(),
        following = userProfile.following.compact(),
        repos = userProfile.publicRepositories.toString()
    )
}

@Composable
private fun Tabs(selectedTab: Tab, onTabSelect: (Tab) -> Unit) {
    Surface(color = MaterialTheme.colors.primary) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            SimpleTab(
                modifier = Modifier.weight(.5f),
                text = "Followers",
                isSelected = selectedTab == Tab.Followers,
                onClick = { onTabSelect(Tab.Followers) }
            )
            SimpleTab(
                modifier = Modifier.weight(.5f),
                text = "Following",
                isSelected = selectedTab == Tab.Following,
                onClick = { onTabSelect(Tab.Following) }
            )
        }
    }
}

private enum class Tab {
    Followers, Following
}