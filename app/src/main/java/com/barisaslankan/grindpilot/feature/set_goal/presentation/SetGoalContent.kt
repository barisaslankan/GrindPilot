@file:OptIn(ExperimentalMaterial3Api::class)

package com.barisaslankan.grindpilot.feature.set_goal.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.barisaslankan.grindpilot.feature.set_goal.presentation.components.TaskItem
import com.barisaslankan.grindpilot.model.ProgressType
import com.barisaslankan.grindpilot.ui.theme.BackgroundColor
import com.barisaslankan.grindpilot.ui.theme.OrangeGP
import com.barisaslankan.grindpilot.ui.theme.TextColor
import com.barisaslankan.grindpilot.ui.theme.Typography

@Composable
fun SetGoalContent(
    modifier: Modifier,
    isTimePickerExtended : Boolean,
    isProgressTypeExpanded : Boolean,
    goalName : String,
    task : String,
    tasks : ArrayList<String>,
    totalWork : Double,
    dismissTimePicker : () -> Unit,
    onProgressTypeExpandedChanged : (Boolean) -> Unit,
    onGoalNameChanged : (String) -> Unit,
    onProgressTypeChanged : (ProgressType) -> Unit,
    onTaskChanged : (String) -> Unit,
    onTaskAdded : (String) -> Unit,
    onTimePicked: (String, String) -> Unit,
    onTotalWorkChanged : (String) -> Unit,
    onGoalSaved : () -> Unit,
    onTaskRemoved : (String) -> Unit,
    onBackPressed : () -> Unit
){

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
            .padding(16.dp)
    ) {

        Column(
            modifier = modifier
                .fillMaxWidth()
                .background(BackgroundColor),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.Start
        ) {

            Row(
                modifier = modifier
            ) {
                IconButton(
                    colors = (IconButtonColors(
                        containerColor = OrangeGP,
                        contentColor = BackgroundColor,
                        disabledContentColor = OrangeGP,
                        disabledContainerColor = BackgroundColor
                    )),
                    onClick = onBackPressed,
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.ArrowBack,
                        contentDescription = "Back Button"
                    )
                }
                Spacer(modifier = modifier.weight(1f))

                IconButton(
                    colors = (
                            IconButtonColors(
                                containerColor = OrangeGP,
                                contentColor = BackgroundColor,
                                disabledContentColor = OrangeGP,
                                disabledContainerColor = BackgroundColor
                            )),
                    onClick = {},
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add Plan Button"
                    )
                }
            }

            Spacer(modifier = modifier.height(8.dp))

            OutlinedTextField(
                modifier = modifier,
                shape = RoundedCornerShape(16.dp),
                value = goalName,
                onValueChange = onGoalNameChanged,
                textStyle = TextStyle(fontStyle = Typography.titleLarge.fontStyle, color = TextColor)
            )

            Text(
                text = "Goal Volume",
                style = TextStyle(fontStyle = Typography.titleLarge.fontStyle, color = TextColor),
            )

            Row(
                modifier = modifier,
            ){

                OutlinedTextField(
                    modifier = modifier.weight(1f),
                    shape = RoundedCornerShape(16.dp),
                    value = totalWork.toString(),
                    onValueChange = onTotalWorkChanged,
                    textStyle = TextStyle(fontStyle = Typography.titleLarge.fontStyle, color = TextColor)
                )

                Spacer(modifier = Modifier.width(16.dp))

                ProgressTypePicker(
                    modifier = modifier.weight(1f),
                    onProgressTypeExpandedChanged = onProgressTypeExpandedChanged,
                    isProgressTypeExpanded = isProgressTypeExpanded,
                    onProgressTypeChanged = {
                        onProgressTypeChanged(it)
                    }
                )
            }

            Row(
                modifier = modifier,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                OutlinedTextField(
                    shape = RoundedCornerShape(16.dp),
                    value = task,
                    onValueChange = onTaskChanged,
                    textStyle = TextStyle(fontStyle = Typography.titleLarge.fontStyle, color = TextColor)
                )

                IconButton(
                    colors = IconButtonColors(
                        containerColor = OrangeGP,
                        contentColor = BackgroundColor,
                        disabledContentColor = BackgroundColor,
                        disabledContainerColor = OrangeGP
                    ),
                    onClick = { onTaskAdded(task) })
                {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Add task button"
                    )
                }
            }

            Text(
                text = "Selected Tasks:",
                style = TextStyle(fontStyle = Typography.titleLarge.fontStyle),
                color = TextColor
            )

            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ){
                items(tasks){task ->
                    Row(modifier = modifier) {
                       TaskItem(task = task) {
                           onTaskRemoved(it)
                       }
                    }
                }
            }

            OutlinedButton(
                modifier = modifier
                    .align(Alignment.CenterHorizontally),
                colors = ButtonColors(
                    containerColor = OrangeGP,
                    contentColor = TextColor,
                    disabledContainerColor = OrangeGP,
                    disabledContentColor = TextColor
                ),
                border = null,
                content = {
                    Text(
                        text = "Save Goal",
                        style = TextStyle(fontStyle = Typography.titleLarge.fontStyle),
                        color = BackgroundColor
                    )
                },
                onClick = onGoalSaved
            )
        }
    }
}

@Composable
fun ProgressTypePicker(
    modifier : Modifier,
    isProgressTypeExpanded : Boolean,
    onProgressTypeChanged : (ProgressType) -> Unit,
    onProgressTypeExpandedChanged: (Boolean) -> Unit
    ){

    ExposedDropdownMenuBox(
        modifier = modifier,
        expanded = isProgressTypeExpanded,
        onExpandedChange = onProgressTypeExpandedChanged
    ) {
        ProgressType.entries.forEach { progressType ->
            DropdownMenuItem(
                text = { Text(
                    text = progressType.name,
                    style = TextStyle(
                        fontStyle = Typography.titleLarge.fontStyle,
                        color = TextColor
                    )
                )},
                onClick = {
                    onProgressTypeChanged(progressType)
                }
            )
        }
    }
}

@Composable
fun TimePicker(
    modifier : Modifier,
    isTimePickerExtended : Boolean,
    dismissTimePicker: () -> Unit,
    onTimePicked : (String,String) -> Unit
) {
    DropdownMenu(
        modifier = modifier,
        expanded = isTimePickerExtended,
        onDismissRequest = dismissTimePicker
    ) {
        for (hour in 0 until 24) {
            for (minute in 0 until 60 step 15) {
                DropdownMenuItem(
                    onClick = {onTimePicked("$hour", if (minute < 10) "0$minute" else "$minute")},
                    text = {Text(text = "$hour:${if (minute < 10) "0$minute" else minute}")}
                )
            }
        }
    }
}

@Preview
@Composable
fun SetGoalContentPreview(){
    SetGoalContent(
        modifier = Modifier.fillMaxWidth(),
        isProgressTypeExpanded = false,
        isTimePickerExtended = false,
        onProgressTypeExpandedChanged = {},
        dismissTimePicker = {},
        goalName = "GoalName",
        onGoalNameChanged = {},
        onProgressTypeChanged = {},
        onTaskAdded = {},
        onTaskChanged = {},
        onTimePicked = { hour, minute ->

        },
        onTotalWorkChanged = {},
        task = "Task",
        totalWork = 0.0,
        onGoalSaved = {},
        tasks = arrayListOf(
            "Task1", "Task2", "Task3"
        ),
        onTaskRemoved = {},
        onBackPressed = {}
    )
}