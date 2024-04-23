package com.barisaslankan.grindpilot.feature_calendar.presentation

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.barisaslankan.grindpilot.core.ui.theme.BackgroundColor
import com.barisaslankan.grindpilot.core.ui.theme.OrangeGP
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarScreen(
    viewModel: CalendarViewModel = hiltViewModel(),
    navigateToSetGoal : () -> Unit,
    navigateToPlans : () -> Unit
){

    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val datePickerState = rememberDatePickerState(initialSelectedDateMillis = Calendar.getInstance().timeInMillis)


    LaunchedEffect(key1 = state.error) {
        state.error?.let {error ->
            Toast.makeText(
                context,
                error,
                Toast.LENGTH_LONG
            ).show()
        }
    }

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
        CalendarScreenContent(
            modifier = Modifier
                .fillMaxWidth(),
            state.goals ?: arrayListOf(),
            datePickerState = datePickerState,
            navigateToPlans = navigateToPlans,
            navigateToSetGoal = navigateToSetGoal
        )
    }
}