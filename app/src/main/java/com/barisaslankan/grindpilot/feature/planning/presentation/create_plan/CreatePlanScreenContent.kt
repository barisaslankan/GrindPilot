package com.barisaslankan.grindpilot.feature.planning.presentation.create_plan

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.barisaslankan.grindpilot.feature.planning.presentation.create_plan.component.GoalItem
import com.barisaslankan.grindpilot.feature.planning.presentation.create_plan.component.PlanTemplate
import com.barisaslankan.grindpilot.model.Goal
import com.barisaslankan.grindpilot.model.Plan
import com.barisaslankan.grindpilot.model.ProgressType
import com.barisaslankan.grindpilot.ui.theme.BackgroundColor
import com.barisaslankan.grindpilot.ui.theme.OrangeGP
import com.barisaslankan.grindpilot.ui.theme.TextColor
import com.barisaslankan.grindpilot.ui.theme.Typography

@Composable
fun CreatePlanScreenContent(
    modifier : Modifier,
    name : String,
    onNameChanged : (String) -> Unit,
    goals : ArrayList<Goal>,
    selectedGoals : ArrayList<Goal>,
    addGoalToPlan : (goal : Goal) -> Unit,
    uploadPlan : () -> Unit,
    onBackButtonClicked : () -> Unit,
    removeGoalFromPlan : (goal : Goal) -> Unit,
){

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
            .padding(16.dp)
    ) {
        Column(
            modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
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
                    onClick = onBackButtonClicked,
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
                    onClick = uploadPlan,
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add Plan Button"
                    )
                }
            }

            OutlinedTextField(
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                value = name,
                onValueChange = onNameChanged,
                textStyle = TextStyle(
                    fontStyle = Typography.bodyMedium.fontStyle,
                    color = TextColor
                ),
                //colors = OutlinedTextFieldDefaults.colors().copy(focusedLabelColor = OrangeGP),
                label = {
                    Text(text = "Plan name", style = TextStyle(
                        fontStyle = Typography.labelSmall.fontStyle,
                        color = TextColor
                    ))
                },
            )

            LazyColumn(
                contentPadding = PaddingValues(all = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(goals) { goal ->
                    GoalItem(
                        goal = goal,
                        addGoalToPlan = addGoalToPlan,
                        isAdded = false,
                        removeGoalFromPlan = removeGoalFromPlan
                    )
                }
            }

           PlanTemplate(modifier = modifier.weight(1f)
               .background(BackgroundColor),
               selectedGoals = selectedGoals,
               addGoalToPlan = addGoalToPlan,
               removeGoalFromPlan = removeGoalFromPlan
           )

            OutlinedButton(
                border = null,
                modifier = modifier
                    .align(Alignment.CenterHorizontally),
                colors = ButtonColors(
                    containerColor = OrangeGP,
                    contentColor = BackgroundColor,
                    disabledContainerColor = OrangeGP,
                    disabledContentColor = BackgroundColor
                ),
                content = {
                    Text(
                        text = "Upload Plan",
                        style = TextStyle(fontStyle = Typography.titleLarge.fontStyle),
                        color = BackgroundColor
                    )
                },
                onClick = uploadPlan
            )
        }
    }
}

@Preview
@Composable
fun CreatePlanScreenContentPreview(){
    CreatePlanScreenContent(
        modifier = Modifier.fillMaxWidth(),
        goals = arrayListOf(
            Goal(
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
            )
        ),
        selectedGoals = arrayListOf(
            Goal(
                "",
                "",
                "Goal3",
                ProgressType.HOURS,
                tasks = null,
                progress = 0.0,
                "",
                100.0,
            ),
            Goal(
                "",
                "",
                "Goal4",
                ProgressType.HOURS,
                tasks = null,
                progress = 0.0,
                "",
                100.0
            )
        ),
        addGoalToPlan = {},
        uploadPlan = {},
        onBackButtonClicked = {},
        removeGoalFromPlan = {},
        name = "",
        onNameChanged = {}
    )
}