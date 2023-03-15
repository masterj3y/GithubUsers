package github.masterj3y.search.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import github.masterj3y.designsystem.components.AppToolbar
import github.masterj3y.designsystem.components.ClickableIcon
import github.masterj3y.designsystem.components.SimpleUserRow
import github.masterj3y.designsystem.theme.LocalDesignSystemViewModel
import kotlinx.coroutines.flow.filterNotNull

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    navigateToFavorites: () -> Unit,
    navigateToProfile: (userName: String) -> Unit
) {

    Scaffold(
        topBar = {
            val userName by viewModel.userName.collectAsState()
            SearchTopAppBar(
                userName = userName,
                onUserNameChange = viewModel::search,
                navigateToFavorites = navigateToFavorites
            )
        }
    ) { paddingValues ->

        val users = viewModel.searchUserResultPages.collectAsLazyPagingItems()
        val isLoadingUsers by remember {
            derivedStateOf { users.loadState.append is LoadState.Loading }
        }
        val isUsersEmpty by remember { derivedStateOf { users.itemCount == 0 } }

        AnimatedVisibility(!isUsersEmpty, enter = fadeIn(), exit = fadeOut()) {
            LazyColumn(modifier = Modifier.padding(paddingValues)) {
                items(users) { user ->
                    if (user != null)
                        SimpleUserRow(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { navigateToProfile(user.login) },
                            profilePicUrl = user.avatarUrl,
                            userName = user.login
                        )
                }

                if (isLoadingUsers)
                    item {
                        LoadingUsers()
                    }
            }
        }

        AnimatedVisibility(isUsersEmpty, enter = fadeIn(), exit = fadeOut()) {
            NoDataAvailable(paddingValues)
        }
    }
}

@Composable
private fun SearchTopAppBar(
    modifier: Modifier = Modifier,
    userName: String,
    onUserNameChange: (String) -> Unit,
    navigateToFavorites: () -> Unit
) {

    Surface(modifier = modifier, color = MaterialTheme.colors.primary) {
        Column {

            AppToolbar(title = "GithubUsers", navigateToFavoriteUsers = navigateToFavorites)

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = MaterialTheme.colors.background,
                    textColor = MaterialTheme.colors.onBackground
                ),
                shape = RoundedCornerShape(20.dp),
                placeholder = {
                    Text(text = "Search Some User")
                },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Search, contentDescription = null)
                },
                value = userName,
                onValueChange = onUserNameChange
            )
        }
    }
}

@Composable
private fun LoadingUsers() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun NoDataAvailable(paddingValues: PaddingValues) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "No data available!")
    }
}