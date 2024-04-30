package com.barisaslankan.grindpilot.feature_planning.presentation.calendar.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.barisaslankan.grindpilot.core.ui.theme.BackgroundColor
import com.barisaslankan.grindpilot.core.ui.theme.MEDIUM_BORDER_WIDTH
import com.barisaslankan.grindpilot.core.ui.theme.MEDIUM_ICON_SIZE
import com.barisaslankan.grindpilot.core.ui.theme.MEDIUM_PADDING
import com.barisaslankan.grindpilot.core.ui.theme.OrangeGP
import com.barisaslankan.grindpilot.core.ui.theme.SMALL_PADDING
import com.barisaslankan.grindpilot.core.ui.theme.TextColor
import com.barisaslankan.grindpilot.core.ui.theme.Typography
import com.barisaslankan.grindpilot.feature_planning.domain.model.Goal
import com.barisaslankan.grindpilot.feature_planning.domain.model.Task

@Composable
fun CalendarItem(
    modifier : Modifier,
    goal : Goal,
    calculateProgress : (Goal) -> Float,
    onTaskCheckedChanged : (Boolean, Task, Goal) -> Unit
) {
    val expandedState = remember { mutableStateOf(true) }

    Column(
        modifier = modifier
            .background(BackgroundColor)
            .padding(SMALL_PADDING)
            .border(
                color = OrangeGP,
                width = MEDIUM_BORDER_WIDTH,
                shape = RoundedCornerShape(MEDIUM_PADDING)
            )
    ) {
        Box(
            modifier = modifier
                .border(
                    color = OrangeGP,
                    width = MEDIUM_BORDER_WIDTH,
                    shape = RoundedCornerShape(MEDIUM_PADDING)
                )
        ) {
            Column(
                modifier = modifier.padding(MEDIUM_PADDING),
                verticalArrangement = Arrangement.spacedBy(MEDIUM_PADDING)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = modifier.weight(1f),
                        text = "00:00",
                        style = Typography.bodyMedium,
                        color = TextColor
                    )
                    IconButton(
                        modifier = Modifier.size(MEDIUM_ICON_SIZE),
                        onClick = { expandedState.value = !expandedState.value },
                    ) {
                        Icon(
                            imageVector = if (expandedState.value) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                            contentDescription = if (expandedState.value) "Collapse Button" else "Expand Button",
                            tint = OrangeGP
                        )
                    }
                }
                Text(
                    modifier = modifier
                        .align(Alignment.CenterHorizontally)
                        .wrapContentHeight(align = Alignment.CenterVertically),
                    text = goal.name,
                    textAlign = TextAlign.Center,
                    style = Typography.titleSmall,
                    color = OrangeGP
                )
                ProgressBar(
                    modifier = modifier,
                    progress = calculateProgress(goal)
                )
            }
        }
        if(expandedState.value && !goal.tasks.isNullOrEmpty()){
            LazyColumn(
                contentPadding = PaddingValues(all = MEDIUM_PADDING),
                verticalArrangement = Arrangement.spacedBy(MEDIUM_PADDING),
                horizontalAlignment = Alignment.CenterHorizontally) {
                items(goal.tasks.toList()){goalTask ->
                    DailyTaskItem(
                        modifier = modifier,
                        task = goalTask,
                        onTaskCheckedChanged = { isChecked, task ->
                            onTaskCheckedChanged(isChecked, task, goal)
                        }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun CalendarItemPreview(){
    CalendarItem(
        modifier = Modifier,
        goal = Goal(name = "Chess", tasks = arrayListOf()),
        calculateProgress = {0.4f},
        onTaskCheckedChanged = {isChecked , task, goal ->}
    )
}