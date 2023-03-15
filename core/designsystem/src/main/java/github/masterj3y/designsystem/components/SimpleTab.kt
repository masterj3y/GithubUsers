package github.masterj3y.designsystem.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun SimpleTab(
    modifier: Modifier = Modifier,
    text: String,
    isSelected: Boolean,
    selectedColor: Color = MaterialTheme.colors.background,
    unselectedColor: Color = MaterialTheme.colors.primary,
    onClick: (String) -> Unit
) {

    val backgroundColor = animateColorAsState(
        targetValue = if (isSelected) selectedColor else unselectedColor
    )


    val textColor = animateColorAsState(
        targetValue = if (!isSelected) selectedColor else unselectedColor
    )

    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        color = backgroundColor.value
    ) {
        Text(
            modifier = Modifier
                .clickable { onClick(text) }
                .padding(horizontal = 4.dp, vertical = 16.dp),
            textAlign = TextAlign.Center,
            color = textColor.value,
            text = text
        )
    }
}