package com.barisaslankan.grindpilot.feature.auth.view.screens.login

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.barisaslankan.grindpilot.feature.auth.view.screens.signup.SignUpScreenContent
import com.barisaslankan.grindpilot.feature.auth.view.screens.signup.SignUpScreenState
import com.barisaslankan.grindpilot.ui.theme.BackgroundColor

@Composable
fun LoginScreen(
    viewModel : LoginViewModel = hiltViewModel(),
    navigateToSignUp : () -> Unit,
    navigateToHome : () -> Unit
){

    val state = viewModel.state.value
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

    LoginScreenContent(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(BackgroundColor),
        onSignInClicked = {
            viewModel.signInWithEmailAndPassword(state.email, state.password)
        },
        email = state.email,
        onEmailChanged = { email ->
            state.copy(email = email)
        },
        password = state.password,
        onPasswordChanged = {password ->
            state.copy(password = password)
        },
        navigateToSignUp = navigateToSignUp
    )
}