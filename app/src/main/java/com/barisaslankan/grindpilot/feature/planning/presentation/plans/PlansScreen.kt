package com.barisaslankan.grindpilot.feature.planning.presentation.plans

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.barisaslankan.grindpilot.ui.theme.BackgroundColor

@Composable
fun PlansScreen(
    viewModel: PlansViewModel = hiltViewModel(),
    navigateToCreatePlan : () -> Unit,
    onBackButtonClicked : () -> Unit
){
    val state by viewModel.state.collectAsState()

    PlansScreenContent(
        modifier = Modifier
            .fillMaxWidth(),
        plans = state.plans,
        onBackButtonClicked = onBackButtonClicked,
        navigateToCreatePlan = navigateToCreatePlan
    )

}