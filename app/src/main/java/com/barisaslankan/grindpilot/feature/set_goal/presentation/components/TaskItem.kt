package com.barisaslankan.grindpilot.feature.set_goal.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.barisaslankan.grindpilot.ui.theme.BackgroundColor
import com.barisaslankan.grindpilot.ui.theme.MEDIUM_PADDING
import com.barisaslankan.grindpilot.ui.theme.OrangeGP
import com.barisaslankan.grindpilot.ui.theme.SMALL_BORDER_WIDTH
import com.barisaslankan.grindpilot.ui.theme.SMALL_PADDING
import com.barisaslankan.grindpilot.ui.theme.TextColor
import com.barisaslankan.grindpilot.ui.theme.Typography

@Composable
fun TaskItem(
    task : String,
    onTaskRemoved : (String) -> Unit
){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = SMALL_BORDER_WIDTH,
                color = OrangeGP,
                shape = RoundedCornerShape(MEDIUM_PADDING)
            )
    ) {
        Row(
            modifier = Modifier.padding(horizontal = MEDIUM_PADDING, vertical = SMALL_PADDING),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = task,
                style = Typography.bodyMedium,
                color = OrangeGP
            )

            Spacer(modifier = Modifier.weight(1f))

            IconButton(
                onClick = { onTaskRemoved(task) },
                colors = IconButtonColors(
                    containerColor = BackgroundColor,
                    contentColor = OrangeGP,
                    disabledContainerColor = BackgroundColor,
                    disabledContentColor = OrangeGP,
                ),
            ){
                Icon(
                    imageVector = Icons.Default.Clear,
                    contentDescription = "Remove Task Button"
                )
            }
        }
    }
    
}

@Preview
@Composable
fun TaskItemPreview(){
    TaskItem(
        task = "asdasd",
        onTaskRemoved = {}
    )
}