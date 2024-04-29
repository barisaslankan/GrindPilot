package com.barisaslankan.grindpilot.feature_planning.presentation.calendar.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.barisaslankan.grindpilot.core.ui.theme.OrangeGP
import com.kizitonwose.calendar.core.CalendarDay

@Composable
fun CalendarDayItem(day: CalendarDay) {
    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .border(width = 1.dp, color = OrangeGP),
        contentAlignment = Alignment.Center
    ) {
        Text(text = day.date.dayOfMonth.toString())
    }
}