package com.barisaslankan.grindpilot.feature.planning.presentation.plans

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.barisaslankan.grindpilot.ui.theme.BackgroundColor
import com.barisaslankan.grindpilot.ui.theme.OrangeGP

@Composable
fun PlansScreen(
    viewModel: PlansViewModel = hiltViewModel(),
    navigateToCreatePlan : () -> Unit,
    onBackButtonClicked : () -> Unit
){
    val state by viewModel.state.collectAsState()


    if(state.isLoading){
        Box(
            Modifier.fillMaxSize()
                .background(color = BackgroundColor)
        ) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
                color = OrangeGP,
            )
        }
    }else {
        PlansScreenContent(
            modifier = Modifier
                .fillMaxWidth(),
            plans = state.plans,
            onBackButtonClicked = onBackButtonClicked,
            navigateToCreatePlan = navigateToCreatePlan
        )
    }
}