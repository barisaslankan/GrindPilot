package com.barisaslankan.grindpilot.feature.auth.view.screens.signup

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
import com.barisaslankan.grindpilot.ui.theme.BackgroundColor

@Composable
fun SignUpScreen(
    signUpViewModel: SignUpViewModel = hiltViewModel(),
    navigateToHome : () -> Unit,
    navigateToLogin : () -> Unit
){

    val state = signUpViewModel.state.value

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

    SignUpScreenContent(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(BackgroundColor),
        onSignUpClicked = {
            signUpViewModel
                .createUserWithEmailAndPassword(
                    email = state.email,
                    password = state.password,
                    repeatPassword= state.repeatPassword
                )},
        email = state.email,
        password = state.password,
        repeatPassword = state.repeatPassword,
        onEmailTextChanged = {email ->
            state.copy(email = email)
        },
        onPasswordTextChanged = {password ->
            state.copy(password = password)
        },
        onRepeatPasswordTextChanged = {repeatPassword ->
            state.copy(repeatPassword = repeatPassword)
        },
        navigateToLogin = navigateToLogin
    )
}