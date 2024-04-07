package com.barisaslankan.grindpilot.feature.planning.presentation.create_plan.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.barisaslankan.grindpilot.model.Goal
import com.barisaslankan.grindpilot.model.ProgressType
import com.barisaslankan.grindpilot.ui.theme.BackgroundColor
import com.barisaslankan.grindpilot.ui.theme.OrangeGP
import com.barisaslankan.grindpilot.ui.theme.Typography

@Composable
fun GoalItem(
    goal : Goal,
    addGoalToPlan: (goal : Goal) -> Unit,
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
                onClick ={ addGoalToPlan(goal) },
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
        addGoalToPlan = {},
        removeGoalFromPlan = {}
    )
}