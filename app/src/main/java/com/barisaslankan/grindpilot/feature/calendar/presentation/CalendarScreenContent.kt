package com.barisaslankan.grindpilot.feature.calendar.presentation

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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.barisaslankan.grindpilot.feature.calendar.presentation.components.CalendarDayItem
import com.barisaslankan.grindpilot.feature.calendar.presentation.components.CalendarItem
import com.barisaslankan.grindpilot.model.Goal
import com.barisaslankan.grindpilot.model.ProgressType
import com.barisaslankan.grindpilot.ui.theme.BackgroundColor
import com.barisaslankan.grindpilot.ui.theme.OrangeGP
import com.barisaslankan.grindpilot.ui.theme.Typography
import com.kizitonwose.calendar.compose.VerticalCalendar

//import io.github.boguszpawlowski.composecalendar.SelectableCalendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarScreenContent(
    modifier: Modifier,
    goals : ArrayList<Goal>,
    datePickerState: DatePickerState
){
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(BackgroundColor)
    ) {

        Column(
            modifier = modifier
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
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
                    onClick = { },
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.List,
                        contentDescription = "Back Button"
                    )
                }
                Spacer(modifier = modifier.width(16.dp))

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
                        width = 1.5.dp,
                        shape = RoundedCornerShape(16.dp)
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
                        width = 1.5.dp,
                        shape = RoundedCornerShape(16.dp)
                    )
                    .weight(5f)
            ) {

                if(goals.size>0)

                    LazyColumn(
                    contentPadding = PaddingValues(all = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(goals) { goal ->
                        CalendarItem(
                            modifier = Modifier.fillMaxWidth(),
                            goal = goal,
                        )
                    }
                } else Text(
                    modifier = modifier.align(Alignment.Center),
                    text = "No plan to be displayed",
                    style = TextStyle(
                        fontStyle = Typography.titleLarge.fontStyle,
                        color = OrangeGP
                    )
                )
            }

            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .border(
                        color = OrangeGP,
                        width = 1.5.dp,
                        shape = RoundedCornerShape(16.dp)
                    )
                    .weight(1.5f)
            ) {

                Row(
                    modifier.fillMaxSize()
                ) {

                    Spacer(modifier = Modifier.weight(1f))



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
    )
}