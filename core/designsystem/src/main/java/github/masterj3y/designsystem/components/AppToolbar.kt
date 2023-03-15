package github.masterj3y.designsystem.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import github.masterj3y.designsystem.theme.LocalDesignSystemViewModel

@Composable
fun AppToolbar(
    modifier: Modifier = Modifier,
    title: String,
    backButton: (@Composable () -> Unit)? = null,
    navigateToFavoriteUsers: () -> Unit
) {

    val designSystemViewModel = LocalDesignSystemViewModel.current
    val systemDarkMode = isSystemInDarkTheme()
    val isDarkModeEnabled by designSystemViewModel.darkModeEnabled.collectAsState()

    Surface(modifier = modifier, color = MaterialTheme.colors.primary) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
                .padding(start = 16.dp, end = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            backButton?.let {
                it.invoke()
                Spacer(modifier = Modifier.width(16.dp))
            }

            Text(
                modifier = Modifier.weight(1f),
                text = title,
                style = MaterialTheme.typography.h6,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            ClickableIcon(
                imageVector = Icons.Default.Favorite,
                contentDescription = null,
                tint = Color.Red,
                onClick = navigateToFavoriteUsers
            )
            ClickableIcon(
                imageVector = if (isDarkModeEnabled
                        ?: systemDarkMode
                ) Icons.Default.LightMode else Icons.Default.DarkMode,
                contentDescription = null,
                onClick = {
                    designSystemViewModel.enableDarkMode(
                        !(isDarkModeEnabled ?: systemDarkMode)
                    )
                }
            )
            ClickableIcon(
                imageVector = Icons.Default.Language,
                contentDescription = null,
                onClick = {}
            )
        }
    }
}