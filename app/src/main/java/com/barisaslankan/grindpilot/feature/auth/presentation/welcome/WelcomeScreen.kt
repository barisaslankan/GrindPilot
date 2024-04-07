package com.barisaslankan.grindpilot.feature.auth.presentation.welcome

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.annotation.Untainted

@Composable
fun WelcomeScreen(
    viewModel: WelcomeViewModel,
    authenticateWithGoogle : () -> Unit,
    navigateToCalendar : () -> Unit,
    navigateToSignUp : () -> Unit
){

    val state by viewModel.state.collectAsStateWithLifecycle()
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

    LaunchedEffect(key1 = Unit) {
        val result = viewModel.isSignedInUser()
        if(result){
            navigateToCalendar()
        }
    }

    LaunchedEffect(key1 = state.user) {
        if(state.user != null) {
            if(!state.userFromDb){
                viewModel.addUserToDb(state.user!!)
            }
            viewModel.resetState()
        }
    }

    WelcomeScreenContent(
        modifier = Modifier.fillMaxSize(),
        navigateToSignUp = navigateToSignUp,
        authenticateWithGoogle = authenticateWithGoogle
    )
}
