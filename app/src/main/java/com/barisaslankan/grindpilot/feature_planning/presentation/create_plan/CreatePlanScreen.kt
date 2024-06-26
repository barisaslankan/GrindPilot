package com.barisaslankan.grindpilot.feature_planning.presentation.create_plan

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.barisaslankan.grindpilot.core.ui.theme.BackgroundColor
import com.barisaslankan.grindpilot.core.ui.theme.OrangeGP

@Composable
fun CreatePlanScreen(
    viewModel: CreatePlanViewModel = hiltViewModel(),
    onBackButtonClicked : () -> Unit,
    navigateToCalendar : () -> Unit
){

    val state by viewModel.state.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(key1 = state.error) {
        state.error?.let {
            Toast.makeText(
                context,
                it,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    if(state.isLoading){
        Box(
            Modifier.fillMaxSize()
                .background(color = BackgroundColor)
        ) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
                color = OrangeGP,
            )
        }

    }else{
        CreatePlanScreenContent(
            modifier = Modifier.fillMaxWidth(),
            goals = state.goals,
            name = state.name,
            onNameChanged = {name ->
                viewModel.onPlanNameChanged(name)
            },
            selectedGoals = state.selectedGoals,
            addGoalToPlan = {goal ->
                viewModel.addGoalToPlan(goal)
            },
            createPlan = {
                viewModel.createPlan(name = state.name, goals = state.selectedGoals, days = state.selectedDays.map { it.name }, duration = state.planDuration)
                navigateToCalendar()
            },
            onBackButtonClicked = onBackButtonClicked,
            removeGoalFromPlan = {goal ->
                viewModel.removeGoalFromPlan(goal)
            },
            onDurationTypeChanged = { viewModel.onDurationTypeChanged(it) },
            isDurationTypeExpanded = state.isDurationTypeExpanded,
            durationText = state.durationText,
            onDurationTextChanged = {viewModel.onDurationTextChanged(it)},
            selectedDays = ArrayList(state.selectedDays),
            onDayPicked = {viewModel.onDayPicked(it)},
            onDurationTypeExpandedChanged = {viewModel.onDurationTypeExpanded(it)},
            displayedDurationType = state.displayedDurationType,
            displayedDurationTypeChanged = {viewModel.onDisplayedDurationTypeChanged(it)},
            onBottomSheetExpanded = { viewModel.onBottomSheetExpanded(it) },
            isBottomSheetExpanded = state.isBottomSheetExpanded,
            onTaskWeightChanged = {goal, task, weight -> viewModel.onTaskWeightChanged(goal = goal, task = task, weight = weight)}
        )
    }
}