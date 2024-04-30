package com.barisaslankan.grindpilot.feature_planning.presentation.calendar.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.barisaslankan.grindpilot.core.ui.theme.BackgroundColor
import com.barisaslankan.grindpilot.core.ui.theme.MEDIUM_PADDING
import com.barisaslankan.grindpilot.core.ui.theme.OrangeGP
import com.barisaslankan.grindpilot.core.ui.theme.TextColor
import com.barisaslankan.grindpilot.core.ui.theme.Typography
import com.barisaslankan.grindpilot.feature_planning.domain.model.Task

@Composable
fun DailyTaskItem(
    modifier: Modifier = Modifier,
    task: Task,
    onTaskCheckedChanged : (Boolean, Task) -> Unit
){

    val checkedState = remember { mutableStateOf(false) }

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Checkbox(
            checked = checkedState.value,
            onCheckedChange = {
                checkedState.value = it
                onTaskCheckedChanged(checkedState.value, task) },
            colors = CheckboxDefaults.colors().copy(
                checkedBoxColor = BackgroundColor,
                uncheckedBoxColor = BackgroundColor,
                checkedCheckmarkColor = OrangeGP
            )
        )
        Spacer(modifier = modifier.width(MEDIUM_PADDING))
        Text(
           modifier = modifier
                .align(Alignment.CenterVertically)
                .wrapContentHeight(align = Alignment.CenterVertically),
            text = task.taskName,
            textAlign = TextAlign.Center,
            style = Typography.bodyMedium,
            color = TextColor
        )
    }
}