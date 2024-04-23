package com.barisaslankan.grindpilot.feature_planning.presentation.create_plan.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.barisaslankan.grindpilot.feature_planning.domain.model.Goal
import com.barisaslankan.grindpilot.feature_planning.domain.model.ProgressType
import com.barisaslankan.grindpilot.core.ui.theme.BackgroundColor
import com.barisaslankan.grindpilot.core.ui.theme.OrangeGP
import com.barisaslankan.grindpilot.core.ui.theme.Typography

@Composable
fun GoalItem(
    goal : Goal,
    removeGoalFromPlan: (goal : Goal) -> Unit,
){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

           Text(
               modifier = Modifier.weight(1f),
               text = goal.name,
               style = Typography.bodyMedium,
               color = OrangeGP
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
    }
}

@Composable
@Preview()
fun GoalItemPreview(){
    GoalItem(
        goal = Goal(
            "",
            "",
            "asdasf",
            ProgressType.HOURS,
            tasks = arrayListOf(),
            progress = 0.0,
            workTime = "",
            0.0
        ),
        removeGoalFromPlan = {}
    )
}