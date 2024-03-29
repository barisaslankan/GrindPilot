package com.barisaslankan.grindpilot.feature.auth.presentation.signup

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.barisaslankan.grindpilot.ui.theme.BackgroundColor

@Composable
fun SignUpScreen(
    signUpViewModel: SignUpViewModel = hiltViewModel(),
    navigateToCalendar : () -> Unit,
    navigateToLogin : () -> Unit
){

    val state by signUpViewModel.state

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
                signUpViewModel.addUserToDb(state.user!!)
            }
            navigateToCalendar()
        }
    }

    SignUpScreenContent(
        modifier = Modifier
            .fillMaxWidth(),
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
            signUpViewModel.onEmailChanged(email)
        },
        onPasswordTextChanged = {password ->
            signUpViewModel.onPasswordChanged(password)
        },
        onRepeatPasswordTextChanged = {repeatPassword ->
            signUpViewModel.onRepeatPasswordChanged(repeatPassword)
        },
        navigateToLogin = navigateToLogin
    )
}