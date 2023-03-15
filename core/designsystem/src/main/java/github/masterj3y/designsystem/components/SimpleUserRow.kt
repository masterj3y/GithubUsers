package github.masterj3y.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@Composable
fun SimpleUserRow(
    modifier: Modifier = Modifier,
    profilePic: @Composable RowScope.() -> Unit,
    userName: @Composable RowScope.() -> Unit,
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
            profilePic()
            Spacer(modifier = Modifier.width(16.dp))
            userName()
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun SimpleUserRow(
    modifier: Modifier = Modifier,
    profilePicUrl: String,
    userName: String
) {
    SimpleUserRow(modifier = modifier, profilePic = {
        GlideImage(
            modifier = Modifier
                .size(100.dp)
                .padding(16.dp)
                .clip(CircleShape),
            model =profilePicUrl,
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
    },
        userName = {
            Text(
                text = userName,
                color = MaterialTheme.colors.onPrimary,
                fontWeight = FontWeight.Bold
            )
        }
    )
}