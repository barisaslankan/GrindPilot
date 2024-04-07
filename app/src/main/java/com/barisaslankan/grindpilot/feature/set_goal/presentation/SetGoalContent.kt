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
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.barisaslankan.grindpilot.core.components.IconButtonWithText
import com.barisaslankan.grindpilot.feature.set_goal.presentation.components.TaskItem
import com.barisaslankan.grindpilot.model.ProgressType
import com.barisaslankan.grindpilot.ui.theme.BackgroundColor
import com.barisaslankan.grindpilot.ui.theme.MEDIUM_PADDING
import com.barisaslankan.grindpilot.ui.theme.OrangeGP
import com.barisaslankan.grindpilot.ui.theme.SMALL_PADDING
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
    onBackPressed : () -> Unit,
    createTask :() -> Unit
){

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
            .padding(MEDIUM_PADDING)
    ) {

        Column(
            modifier = modifier
                .fillMaxWidth()
                .background(BackgroundColor),
            verticalArrangement = Arrangement.spacedBy(MEDIUM_PADDING),
            horizontalAlignment = Alignment.Start
        ) {

            Row(
                modifier = modifier,
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
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

                Text(
                    text = "Create Goal",
                    style = Typography.titleMedium,
                    color = OrangeGP
                )

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
                        imageVector = Icons.Default.Check,
                        contentDescription = "Add Plan Button"
                    )
                }
            }

            Spacer(modifier = modifier.height(SMALL_PADDING))

            OutlinedTextField(
                modifier = modifier,
                shape = RoundedCornerShape(MEDIUM_PADDING),
                value = goalName,
                onValueChange = onGoalNameChanged,
                textStyle = Typography.bodyMedium,
                colors = TextFieldDefaults.colors(
                    unfocusedTextColor = TextColor,
                    focusedTextColor = TextColor,
                    focusedContainerColor = BackgroundColor,
                    unfocusedContainerColor = BackgroundColor
                )
            )

            Row(
                modifier = modifier,
            ){

                OutlinedTextField(
                    modifier = modifier.weight(1f),
                    shape = RoundedCornerShape(MEDIUM_PADDING),
                    value = totalWork.toString(),
                    onValueChange = onTotalWorkChanged,

                    textStyle = Typography.bodyMedium,
                    colors = TextFieldDefaults.colors(
                        unfocusedTextColor = TextColor,
                        focusedTextColor = TextColor,
                        focusedContainerColor = BackgroundColor,
                        unfocusedContainerColor = BackgroundColor
                    )
                )

                Spacer(modifier = Modifier.width(MEDIUM_PADDING))

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
                verticalAlignment = Alignment.CenterVertically,
            ) {
                IconButtonWithText(
                    onClick = createTask,
                    icon = Icons.Default.Add,
                    text = "Add Task",
                    color = OrangeGP
                )
            }

            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(SMALL_PADDING)
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
                        style = Typography.bodyMedium,
                        color = BackgroundColor
                    )
                },
                onClick = onGoalSaved
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
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
                    style = Typography.bodyMedium,
                    color = TextColor
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
                    text = {
                        Text(
                            text = "$hour:${if (minute < 10) "0$minute" else minute}",
                            style = Typography.bodyMedium,
                            color = TextColor
                        )
                    }
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
        goalName = "Goal Name",
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
        onBackPressed = {},
        createTask = {}
    )
}