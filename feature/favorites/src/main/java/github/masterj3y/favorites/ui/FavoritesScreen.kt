package github.masterj3y.favorites.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import github.masterj3y.data.extension.compact
import github.masterj3y.data.model.UserProfile
import github.masterj3y.designsystem.components.ClickableIcon
import github.masterj3y.designsystem.components.FFR

@Composable
fun FavoritesScreen(viewModel: FavoritesViewModel = hiltViewModel(), onBackClick: () -> Unit) {

    val favorites by viewModel.favorites.collectAsState()

    Column {

        FavoritesTopBar(onBackClick = onBackClick)

        LazyColumn {
            items(favorites) {
                FavoriteUser(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 16.dp), userProfile = it
                )
            }
        }
    }
}

@Composable
private fun FavoritesTopBar(modifier: Modifier = Modifier, onBackClick: () -> Unit) {
    Surface(modifier = modifier, color = MaterialTheme.colors.primary) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
                .padding(start = 16.dp, end = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            ClickableIcon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null,
                onClick = onBackClick
            )

            Spacer(modifier = Modifier.weight(1f))

            ClickableIcon(
                imageVector = Icons.Default.Settings,
                contentDescription = null,
                onClick = {}
            )
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun FavoriteUser(
    modifier: Modifier = Modifier,
    userProfile: UserProfile,
    backgroundColor: Brush = Brush.horizontalGradient(
        listOf(
            Color.Transparent,
            MaterialTheme.colors.primary
        )
    ),
    shape: Shape = RoundedCornerShape(25.dp),
    padding: PaddingValues = PaddingValues(8.dp),
    elevation: Dp = 12.dp
) {

    Surface(modifier = Modifier.padding(padding), shape = shape, elevation = elevation) {
        Row(
            modifier = Modifier
                .clip(shape)
                .background(brush = backgroundColor)
                .then(modifier),
            verticalAlignment = Alignment.CenterVertically
        ) {
            GlideImage(
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape),
                model = userProfile.avatarUrl,
                contentScale = ContentScale.Crop,
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(text = userProfile.login)
                userProfile.location?.let {
                    Text(text = it)
                }
                userProfile.company?.let {
                    Text(text = it)
                }
                FFR(
                    followers = userProfile.followers.compact(),
                    following = userProfile.following.compact(),
                    repos = userProfile.publicRepositories.toString()
                )
            }
        }
    }
}

@Composable
private fun InfoRow(icon: ImageVector, text: String) {

}