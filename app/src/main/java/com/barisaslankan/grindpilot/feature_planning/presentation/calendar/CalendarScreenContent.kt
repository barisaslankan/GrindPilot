package com.barisaslankan.grindpilot.feature_planning.presentation.calendar

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.barisaslankan.grindpilot.core.ui.theme.BackgroundColor
import com.barisaslankan.grindpilot.core.ui.theme.MEDIUM_BORDER_WIDTH
import com.barisaslankan.grindpilot.core.ui.theme.MEDIUM_PADDING
import com.barisaslankan.grindpilot.core.ui.theme.OrangeGP
import com.barisaslankan.grindpilot.core.ui.theme.SMALL_PADDING
import com.barisaslankan.grindpilot.core.ui.theme.Typography
import com.barisaslankan.grindpilot.feature_planning.domain.model.Goal
import com.barisaslankan.grindpilot.feature_planning.domain.model.Plan
import com.barisaslankan.grindpilot.feature_planning.presentation.calendar.components.CalendarDayItem
import com.barisaslankan.grindpilot.feature_planning.presentation.calendar.components.CalendarItem
import com.kizitonwose.calendar.compose.VerticalCalendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarScreenContent(
    modifier: Modifier,
    dailyPlan : Plan,
    datePickerState: DatePickerState,
    navigateToPlans : () -> Unit,
    navigateToSetGoal : () -> Unit,
    calculateProgress : (Goal) -> Float
){
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(BackgroundColor)
    ) {

        Column(
            modifier = modifier
                .padding(MEDIUM_PADDING),
            verticalArrangement = Arrangement.spacedBy(MEDIUM_PADDING)
        ) {

            Row(
                modifier = modifier
            ) {

                Spacer(modifier = modifier.weight(1f))

                IconButton(
                    colors = (IconButtonColors(
                        containerColor = OrangeGP,
                        contentColor = BackgroundColor,
                        disabledContentColor = OrangeGP,
                        disabledContainerColor = BackgroundColor
                    )),
                    onClick = navigateToPlans,
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.List,
                        contentDescription = "Plans Button"
                    )
                }
                Spacer(modifier = Modifier.width(MEDIUM_PADDING))

                IconButton(
                    colors = (
                            IconButtonColors(
                                containerColor = OrangeGP,
                                contentColor = BackgroundColor,
                                disabledContentColor = OrangeGP,
                                disabledContainerColor = BackgroundColor
                            )),
                    onClick = navigateToSetGoal,
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add Plan Button"
                    )
                }
            }

            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .border(
                        color = OrangeGP,
                        width = MEDIUM_BORDER_WIDTH,
                        shape = RoundedCornerShape(MEDIUM_PADDING)
                    )
                    .weight(2.5f),
                contentAlignment = Alignment.Center,
            ) {

                VerticalCalendar(
                    dayContent = {calendarDay ->
                    CalendarDayItem(day = calendarDay)
                })
            }

            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .border(
                        color = OrangeGP,
                        width = MEDIUM_BORDER_WIDTH,
                        shape = RoundedCornerShape(MEDIUM_PADDING)
                    )
                    .weight(5f)
            ) {

                if(dailyPlan.goals.size>0)
                    LazyColumn(
                    contentPadding = PaddingValues(all = SMALL_PADDING),
                    verticalArrangement = Arrangement.spacedBy(SMALL_PADDING),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(dailyPlan.goals) { goal ->
                        CalendarItem(
                            modifier = Modifier.fillMaxWidth(),
                            goal = goal,
                            calculateProgress = {calculateProgress(goal)}
                        )
                    }
                } else Text(
                    modifier = modifier.align(Alignment.Center),
                    textAlign = TextAlign.Center,
                    text = "No plan to be displayed",
                    style = Typography.bodyMedium,
                    color = OrangeGP
                )
            }

            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .border(
                        color = OrangeGP,
                        width = MEDIUM_BORDER_WIDTH,
                        shape = RoundedCornerShape(MEDIUM_PADDING)
                    )
                    .weight(1.5f)
            ) {

                Row(
                    modifier.fillMaxWidth()
                ) {

                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun CalendarScreenPreview(){
    CalendarScreenContent(
        modifier = Modifier,
        datePickerState = rememberDatePickerState(),
        navigateToPlans = {},
        navigateToSetGoal = {},
        calculateProgress = {0.4f},
        dailyPlan = Plan()
    )
}