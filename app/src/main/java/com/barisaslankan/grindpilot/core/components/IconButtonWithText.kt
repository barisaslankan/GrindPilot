package com.barisaslankan.grindpilot.core.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.barisaslankan.grindpilot.core.ui.theme.MEDIUM_BORDER_WIDTH
import com.barisaslankan.grindpilot.core.ui.theme.SMALL_PADDING
import com.barisaslankan.grindpilot.core.ui.theme.Typography

@Composable
fun IconButtonWithText(
    onClick: () -> Unit,
    icon: ImageVector,
    text: String,
    color : Color,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.clickable { onClick() }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(SMALL_PADDING)
        ) {
            Box(modifier = Modifier.border(
                width = MEDIUM_BORDER_WIDTH,
                color = color,
                shape = CircleShape
            )){
                Icon(
                    imageVector = icon,
                    contentDescription = "Button Icon",
                    tint = color,
                )
            }
            Text(
                text = text,
                style = Typography.bodyMedium,
                color = color
            )
        }
    }
}