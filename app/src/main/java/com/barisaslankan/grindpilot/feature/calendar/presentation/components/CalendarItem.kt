package com.barisaslankan.grindpilot.feature.calendar.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.barisaslankan.grindpilot.core.model.Goal
import com.barisaslankan.grindpilot.core.ui.theme.BackgroundColor
import com.barisaslankan.grindpilot.core.ui.theme.MEDIUM_BORDER_WIDTH
import com.barisaslankan.grindpilot.core.ui.theme.MEDIUM_PADDING
import com.barisaslankan.grindpilot.core.ui.theme.OrangeGP
import com.barisaslankan.grindpilot.core.ui.theme.TextColor
import com.barisaslankan.grindpilot.core.ui.theme.Typography

@Composable
fun CalendarItem(
    modifier : Modifier,
    goal : Goal?,
) {

    var isExpanded : Boolean = true

    val progress = goal?.let {
        goal.totalWork/goal.progress
    } ?: 0.0f

    Box( modifier = modifier
        .padding(8.dp)
        .border(
            color = OrangeGP,
            width = MEDIUM_BORDER_WIDTH,
            shape = RoundedCornerShape(MEDIUM_PADDING)
        )
        .height(120.dp)
    ){
        Column(
            modifier = modifier.padding(MEDIUM_PADDING)
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = modifier
                        .weight(1f),
                    text = "00:00",
                    style = Typography.bodyMedium,
                    color = TextColor
                )

                IconButton(
                    modifier = Modifier.size(24.dp),
                    onClick = {
                        isExpanded = !isExpanded
                    },
                ) {
                    if(!isExpanded)
                    Icon(
                        Icons.Default.KeyboardArrowDown,
                        contentDescription = "Expand Button",
                        tint = OrangeGP
                    )
                    else{
                        Icon(
                            Icons.Default.KeyboardArrowUp,
                            contentDescription = "Collapse Button",
                            tint = OrangeGP
                        )
                    }
                }

            }

            Text(
                modifier = modifier
                    .weight(1f)
                    .align(Alignment.CenterHorizontally)
                    .wrapContentHeight(align = Alignment.CenterVertically),
                text = "Chess",
                textAlign = TextAlign.Center,
                style = Typography.bodyMedium,
                color = OrangeGP
            )

            ProgressBar(
                modifier = modifier.weight(1f),
                progress = progress.toFloat()
            )

            if(isExpanded){
                //tasklar
            }
        }
    }
}

@Preview
@Composable
fun PlanItemPreview(){
    CalendarItem(
        modifier = Modifier.background(BackgroundColor),
        goal = null,
    )
}