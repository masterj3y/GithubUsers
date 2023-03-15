package github.masterj3y.designsystem.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun FFR(modifier: Modifier = Modifier, followers: String, following: String, repos: String) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        val list = remember {
            listOf(
                "Followers" to followers,
                "Following" to following,
                "Repositories" to repos
            )
        }

        list.forEach { item ->
            FFRItem(
                modifier = Modifier.weight(1f / list.size),
                title = item.first,
                count = item.second
            )
        }
    }
}

@Composable
fun FFRItem(modifier: Modifier = Modifier, title: String, count: String) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = title, maxLines = 1, overflow = TextOverflow.Ellipsis)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = count, fontWeight = FontWeight.Bold)
    }
}