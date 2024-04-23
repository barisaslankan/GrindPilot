package com.barisaslankan.grindpilot.feature_planning.presentation.plans

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.barisaslankan.grindpilot.feature_planning.domain.model.Plan
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import com.barisaslankan.grindpilot.feature_planning.presentation.plans.components.PlanItem
import com.barisaslankan.grindpilot.feature_planning.domain.model.Goal
import com.barisaslankan.grindpilot.feature_planning.domain.model.ProgressType
import com.barisaslankan.grindpilot.core.ui.theme.BackgroundColor
import com.barisaslankan.grindpilot.core.ui.theme.LARGE_PADDING
import com.barisaslankan.grindpilot.core.ui.theme.MEDIUM_PADDING
import com.barisaslankan.grindpilot.core.ui.theme.OrangeGP

@Composable
fun PlansScreenContent(
    modifier : Modifier,
    plans : ArrayList<Plan>?,
    onBackButtonClicked : () -> Unit,
    navigateToCreatePlan : () -> Unit
){
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(BackgroundColor)
    ) {
        Column(
            modifier = modifier.padding(MEDIUM_PADDING)
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
                    onClick = navigateToCreatePlan,
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add Plan Button"
                    )
                }
            }

            Spacer(modifier = modifier.height(LARGE_PADDING))

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(MEDIUM_PADDING),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(plans ?: arrayListOf()) { plan ->
                    PlanItem(plan = plan)
                }
            }
        }
    }
}

@Preview
@Composable
fun PlansScreenContentPreview(){
    PlansScreenContent(
        modifier = Modifier.fillMaxWidth(),
        onBackButtonClicked = {},
        navigateToCreatePlan = {},
        plans = arrayListOf(
            Plan(
                name = "Plan1",
                id = "",
                ownerId = "",
                goals = arrayListOf(
                Goal(
                    "",
                    "",
                    "",
                    ProgressType.HOURS,
                    tasks = null,
                    progress = 0.0,
                    "",
                    100.0,
                ),
                Goal(
                    "",
                    "",
                    "",
                    ProgressType.HOURS,
                    tasks = null,
                    progress = 0.0,
                    "",
                    100.0
                )
            )
        ),
            Plan(
                name = "Plan2",
                id = "",
                ownerId = "",
                goals = arrayListOf(
                    Goal(
                        "",
                        "",
                        "",
                        ProgressType.HOURS,
                        tasks = null,
                        progress = 0.0,
                        "",
                        100.0,
                    ),
                    Goal(
                        "",
                        "",
                        "",
                        ProgressType.HOURS,
                        tasks = null,
                        progress = 0.0,
                        "",
                        100.0
                    )
                )
            ),
        )
    )
}