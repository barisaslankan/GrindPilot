package com.barisaslankan.grindpilot.feature.auth.presentation.welcome

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun WelcomeScreen(
    viewModel: WelcomeViewModel,
    authenticateWithGoogle : () -> Unit,
    navigateToCalendar : () -> Unit,
    navigateToSignUp : () -> Unit
){

    val state by viewModel.state
    val context = LocalContext.current

    LaunchedEffect(key1 = state.error) {
        state.error?.let { error ->
            Toast.makeText(
                context,
                error,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    LaunchedEffect(key1 = state.user) {
        if(state.user != null) {
            Toast.makeText(
                context,
                "Sign in successful",
                Toast.LENGTH_LONG
            ).show()
            if(!state.userFromDb){
                viewModel.addUserToDb(state.user!!)
            }
            navigateToCalendar()
            viewModel.resetState()
        }
    }

    WelcomeScreenContent(
        modifier = Modifier.fillMaxSize(),
        navigateToSignUp = navigateToSignUp,
        authenticateWithGoogle = authenticateWithGoogle
    )
}
