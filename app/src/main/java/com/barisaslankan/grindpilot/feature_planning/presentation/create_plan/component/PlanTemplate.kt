package com.barisaslankan.grindpilot.feature_planning.presentation.create_plan.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.barisaslankan.grindpilot.core.ui.theme.BackgroundColor
import com.barisaslankan.grindpilot.core.ui.theme.CORNER_RADIUS
import com.barisaslankan.grindpilot.core.ui.theme.MEDIUM_PADDING
import com.barisaslankan.grindpilot.core.ui.theme.SMALL_BORDER_WIDTH
import com.barisaslankan.grindpilot.core.ui.theme.SMALL_PADDING
import com.barisaslankan.grindpilot.core.ui.theme.TextColor
import com.barisaslankan.grindpilot.feature_planning.domain.model.Goal
import com.barisaslankan.grindpilot.feature_planning.domain.model.Task

@Composable
fun PlanTemplate(
    modifier : Modifier,
    selectedGoals : ArrayList<Goal>,
    removeGoalFromPlan: (goal : Goal) -> Unit,
    planDuration : Double,
    onTaskWeightChanged: (Goal, Task, Double) -> Unit
){
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(BackgroundColor)
            .border(
                width = SMALL_BORDER_WIDTH,
                color = TextColor,
                shape = RoundedCornerShape(CORNER_RADIUS)
            )
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = MEDIUM_PADDING, horizontal = SMALL_PADDING),
            verticalArrangement = Arrangement.spacedBy(SMALL_PADDING),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            selectedGoals.forEach { goal ->
                GoalItem(
                    goal = goal,
                    removeGoalFromPlan = removeGoalFromPlan,
                    planDuration = planDuration,
                    onTaskWeightChanged = onTaskWeightChanged
                )
            }
        }
    }
}

@Preview
@Composable
fun PlanTemplatePreview(){
    PlanTemplate(
        modifier = Modifier,
        selectedGoals = arrayListOf(
            Goal(name = "Goal1"),
            Goal(name = "Goal2")
        ),
        removeGoalFromPlan = {},
        planDuration = 0.0,
        onTaskWeightChanged = {goal, task, weight ->  }
    )
}