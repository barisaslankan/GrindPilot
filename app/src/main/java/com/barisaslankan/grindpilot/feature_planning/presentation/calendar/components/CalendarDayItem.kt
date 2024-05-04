package com.barisaslankan.grindpilot.feature_planning.presentation.calendar.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.barisaslankan.grindpilot.core.ui.theme.HintColor
import com.barisaslankan.grindpilot.core.ui.theme.OrangeGP
import com.barisaslankan.grindpilot.core.ui.theme.SMALL_BORDER_WIDTH
import com.barisaslankan.grindpilot.core.ui.theme.TextColor
import com.barisaslankan.grindpilot.core.ui.theme.Typography
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.DayPosition

@Composable
fun CalendarDayItem(
    day: CalendarDay,
    onCalendarDayClicked : (CalendarDay) -> Unit
) {
    Box(
        modifier = Modifier
            .aspectRatio(1.5f)
            .border(width = SMALL_BORDER_WIDTH, color = OrangeGP)
            .clickable(
                enabled = day.position == DayPosition.MonthDate,
                onClick = { onCalendarDayClicked(day) }
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = day.date.dayOfMonth.toString(),
            style = Typography.bodyMedium,
            color = if (day.position == DayPosition.MonthDate) TextColor else HintColor
        )
    }
}