package com.barisaslankan.grindpilot.feature.planning.presentation.plans.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.barisaslankan.grindpilot.core.model.Plan
import com.barisaslankan.grindpilot.core.ui.theme.MEDIUM_PADDING
import com.barisaslankan.grindpilot.core.ui.theme.OrangeGP
import com.barisaslankan.grindpilot.core.ui.theme.SMALL_BORDER_WIDTH
import com.barisaslankan.grindpilot.core.ui.theme.SMALL_PADDING
import com.barisaslankan.grindpilot.core.ui.theme.TextColor
import com.barisaslankan.grindpilot.core.ui.theme.Typography

@Composable
fun PlanItem(
    plan : Plan
){

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = SMALL_BORDER_WIDTH,
                color = OrangeGP,
                shape = RoundedCornerShape(MEDIUM_PADDING)
            )
    ){
        Row(
            modifier = Modifier.padding(horizontal = MEDIUM_PADDING, vertical = SMALL_PADDING).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = plan.name,
                style = Typography.bodyMedium,
                color = OrangeGP
            )
        }
    }
}