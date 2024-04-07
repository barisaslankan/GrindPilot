package com.barisaslankan.grindpilot.feature.planning.presentation.create_plan.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.barisaslankan.grindpilot.model.Goal
import com.barisaslankan.grindpilot.model.ProgressType
import com.barisaslankan.grindpilot.ui.theme.OrangeGP
import com.barisaslankan.grindpilot.ui.theme.TextColor

@Composable
fun PlanTemplate(
    modifier : Modifier,
    selectedGoals : ArrayList<Goal>,
    addGoalToPlan: (goal : Goal) -> Unit,
    removeGoalFromPlan: (goal : Goal) -> Unit,
){
    Box(modifier = modifier.fillMaxWidth()
        .border(
            width = 1.dp,
            color = TextColor,
            shape = RoundedCornerShape(16.dp)
        )) {

        LazyColumn(
            modifier = Modifier.padding(vertical = 16.dp, horizontal = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(selectedGoals) { goal ->
                GoalItem(
                    goal = goal,
                    addGoalToPlan = addGoalToPlan,
                    removeGoalFromPlan = removeGoalFromPlan
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
        selectedGoals = arrayListOf(Goal(
            "",
            "",
            "Goal1",
            ProgressType.HOURS,
            tasks = null,
            progress = 0.0,
            "",
            100.0,
        ),
            Goal(
                "",
                "",
                "Goal2",
                ProgressType.HOURS,
                tasks = null,
                progress = 0.0,
                "",
                100.0
            )),
        addGoalToPlan = {},
        removeGoalFromPlan = {}
    )
}