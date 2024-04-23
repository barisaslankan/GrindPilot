package com.barisaslankan.grindpilot.feature_auth.presentation.login

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun LoginScreen(
    viewModel : LoginViewModel = hiltViewModel(),
    navigateToSignUp : () -> Unit,
    navigateToCalendar : () -> Unit
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
            if(!state.userFromDb){
                viewModel.addUserToDb(state.user!!)
            }
            navigateToCalendar()
        }
    }

    LoginScreenContent(
        modifier = Modifier
            .fillMaxWidth(),
        onSignInClicked = {
            viewModel.signInWithEmailAndPassword(state.email, state.password)
        },
        email = state.email,
        onEmailChanged = { email ->
            viewModel.onEmailChanged(email)
        },
        password = state.password,
        onPasswordChanged = {password ->
            viewModel.onPasswordChanged(password = password)
        },
        navigateToSignUp = navigateToSignUp
    )
}