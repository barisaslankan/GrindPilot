package com.barisaslankan.grindpilot.feature.planning.presentation.create_plan.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.barisaslankan.grindpilot.core.model.Day
import com.barisaslankan.grindpilot.core.ui.theme.OrangeGP
import com.barisaslankan.grindpilot.core.ui.theme.TextColor
import com.barisaslankan.grindpilot.core.ui.theme.Typography
import java.util.Locale

@Composable
fun DayPicker(
    modifier : Modifier,
    onDayPicked : (Day) -> Unit,
    selectedDays : ArrayList<Day>
){

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Day.entries.forEach(){ day ->
            Text(
                modifier = Modifier.clickable { onDayPicked(day) },
                text = day.name.substring(0, 1) + day.name.substring(1).lowercase(),
                color = if(selectedDays.contains(day)) OrangeGP else TextColor,
                style = Typography.bodyMedium
            )
        }
    }
}

@Preview
@Composable
fun DayPickerPreview(){
    DayPicker(
        modifier = Modifier.fillMaxWidth(),
        onDayPicked = {},
        selectedDays = arrayListOf()
    )
}