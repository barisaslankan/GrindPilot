package com.barisaslankan.grindpilot.feature_planning.presentation.set_goal

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun SetGoalScreen(
    viewModel: SetGoalViewModel = hiltViewModel(),
    onBackPressed : () -> Unit
){

    val state by viewModel.state
    val context = LocalContext.current

    LaunchedEffect(key1 = state.error) {
        state.error?.let { error ->
            Toast.makeText(
                context,
                error,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    SetGoalContent(
        modifier = Modifier
            .fillMaxWidth(),
        goalName = state.goalName,
        onGoalNameChanged = {goalName ->
            viewModel.onGoalNameChanged(goalName)
        },
        isProgressTypeExpanded = state.isProgressTypeExpanded,
        onProgressTypeChanged = {progressType ->
            viewModel.onProgressTypeChanged(progressType = progressType)
        },
        onTaskAdded = {task ->
            viewModel.onTaskAdded(task)
        },
        onTaskChanged = {task ->
            viewModel.onTaskChanged(task = task)
        },
        onTotalWorkChanged = {totalWork ->
            viewModel.onTotalWorkChanged(totalWork = totalWork)
        },
        task = state.task,
        tasks = state.tasks,
        totalWork = state.totalWork,
        onGoalSaved = {
            viewModel.creteGoal(
                progressType = state.progressType,
                name = state.goalName,
                tasks = state.tasks,
                totalWork = state.totalWork,
                workTime = state.workTime
            )
        },
        onTaskRemoved = {task ->
            viewModel.onTaskRemoved(task)
        },
        onBackPressed = onBackPressed,
        onProgressTypeExpandedChanged = {isExpanded ->
            viewModel.onProgressTypeExpandedChanged(isExpanded)
        },
        createTask = {viewModel.onTaskAdded(it)},
        onDisplayedProgressTypeChanged = {displayedProgressType ->
            viewModel.onDisplayedProgressTypeChanged(displayedProgressType)
        },
        displayedProgressType = state.displayedProgressType,
        taskText = state.taskText,
        onTaskTextChanged = {viewModel.onTaskTextChanged(it)},
        displayTaskDialog = state.displayTaskDialog,
        onTaskDialogDisplayed = {viewModel.onTaskDialogDisplayed(it)}
    )
}