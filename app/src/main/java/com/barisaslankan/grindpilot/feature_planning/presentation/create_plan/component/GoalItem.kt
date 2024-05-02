package com.barisaslankan.grindpilot.feature_planning.presentation.create_plan.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.barisaslankan.grindpilot.core.ui.theme.BackgroundColor
import com.barisaslankan.grindpilot.core.ui.theme.CORNER_RADIUS
import com.barisaslankan.grindpilot.core.ui.theme.MEDIUM_PADDING
import com.barisaslankan.grindpilot.core.ui.theme.OrangeGP
import com.barisaslankan.grindpilot.core.ui.theme.SMALL_PADDING
import com.barisaslankan.grindpilot.core.ui.theme.TextColor
import com.barisaslankan.grindpilot.core.ui.theme.Typography
import com.barisaslankan.grindpilot.feature_planning.domain.model.Goal
import com.barisaslankan.grindpilot.feature_planning.domain.model.ProgressType
import com.barisaslankan.grindpilot.feature_planning.domain.model.Task

@Composable
fun GoalItem(
    goal : Goal,
    planDuration : Double,
    removeGoalFromPlan: (Goal) -> Unit,
    onTaskWeightChanged: (Goal, Task, Double) -> Unit
){
    val taskWeight = planDuration/goal.totalWork
    val taskWeightPickerExpandState = remember { mutableStateOf(false) }
    val displayedTaskWeightState = remember { mutableStateOf(taskWeight.toString()) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = MEDIUM_PADDING)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = SMALL_PADDING)
        ){
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = goal.name,
                    style = Typography.bodyMedium,
                    color = TextColor
                )
                IconButton(
                    onClick ={ removeGoalFromPlan(goal) },
                    colors = IconButtonColors(
                        containerColor = BackgroundColor,
                        contentColor = OrangeGP,
                        disabledContentColor = OrangeGP,
                        disabledContainerColor = OrangeGP
                    )
                ) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "Remove Goal from Plan Button",
                    )
                }
            }

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(SMALL_PADDING),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                goal.tasks?.let {taskList ->
                    items(taskList){task ->
                        Row(modifier = Modifier.fillMaxWidth()){
                            Text(
                                modifier = Modifier.weight(0.6f),
                                text = task.taskName,
                                maxLines = 1,
                                style = Typography.bodyMedium,
                                color = OrangeGP
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            TaskWeightPicker(
                                modifier = Modifier.weight(0.4f),
                                isTaskWeightPickerExpanded = taskWeightPickerExpandState.value,
                                onTaskWeightChanged = {goal, task, weight -> onTaskWeightChanged(goal, task, weight) },
                                onTaskWeightPickerExpandedChanged = {taskWeightPickerExpandState.value = it},
                                displayedTaskWeightChanged = {displayedTaskWeightState.value = it},
                                displayedTaskWeight = displayedTaskWeightState.value,
                                goal = goal,
                                planDuration = planDuration,
                                task = task
                            )
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskWeightPicker(
    modifier: Modifier,
    isTaskWeightPickerExpanded: Boolean,
    onTaskWeightChanged: (Goal, Task, Double) -> Unit,
    onTaskWeightPickerExpandedChanged: (Boolean) -> Unit,
    displayedTaskWeightChanged: (String) -> Unit,
    displayedTaskWeight: String,
    task : Task,
    goal: Goal,
    planDuration: Double
) {
    val maxWeight = if (goal.progressType == ProgressType.HOURS.name) 24.0 else planDuration/goal.totalWork
    val weightValues = (0..maxWeight.toInt()).map { it.toDouble() }

    ExposedDropdownMenuBox(
        modifier = modifier,
        expanded = isTaskWeightPickerExpanded,
        onExpandedChange = onTaskWeightPickerExpandedChanged
    ) {
        TextField(
            value = displayedTaskWeight,
            onValueChange = displayedTaskWeightChanged,
            maxLines = 1,
            readOnly = true,
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isTaskWeightPickerExpanded) },
            modifier = Modifier.menuAnchor(),
            textStyle = Typography.bodyMedium,
            colors = TextFieldDefaults.colors(
                unfocusedTextColor = TextColor,
                focusedTextColor = TextColor,
                focusedContainerColor = BackgroundColor,
                unfocusedContainerColor = BackgroundColor
            ),
            shape = RoundedCornerShape(CORNER_RADIUS)
        )
        ExposedDropdownMenu(
            expanded = isTaskWeightPickerExpanded,
            onDismissRequest = { onTaskWeightPickerExpandedChanged(false) },
            modifier = Modifier.background(BackgroundColor)
        ) {
            weightValues.forEach { weight ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = weight.toString(),
                            style = Typography.bodyMedium,
                            color = TextColor
                        )
                    },
                    onClick = {
                        onTaskWeightChanged(goal, task, weight)
                        displayedTaskWeightChanged(weight.toString())
                        onTaskWeightPickerExpandedChanged(false)
                    }
                )
            }
        }
    }
}

@Composable
@Preview
fun GoalItemPreview(){
    GoalItem(
        goal = Goal(
            name = "Goal1",
        ),
        planDuration = 0.0,
        removeGoalFromPlan = {},
        onTaskWeightChanged = {goal, task, weight ->}
    )
}