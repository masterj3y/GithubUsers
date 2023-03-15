package github.masterj3y.designsystem.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun ClickableIcon(
    imageVector: ImageVector,
    contentDescription: String?,
    tint: Color = LocalContentColor.current.copy(alpha = LocalContentAlpha.current),
    onClick: () -> Unit
) {
    Icon(
        modifier = Modifier
            .clip(CircleShape)
            .clickable { onClick() }
            .padding(12.dp),
        imageVector = imageVector,
        tint = tint,
        contentDescription = contentDescription
    )
}