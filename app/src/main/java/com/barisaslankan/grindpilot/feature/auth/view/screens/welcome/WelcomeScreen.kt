package com.barisaslankan.grindpilot.feature.auth.view.screens.welcome

import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.barisaslankan.grindpilot.feature.auth.view.screens.login.LoginViewModel
import com.google.android.gms.auth.api.identity.Identity
import kotlinx.coroutines.launch

@Composable
fun WelcomeScreen(
    viewModel: WelcomeViewModel,
    authenticateWithGoogle : () -> Unit,
    navigateToHome : () -> Unit,
    navigateToSignUp : () -> Unit
){

    val context = LocalContext.current
    val state = viewModel.state.value


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

            navigateToHome()
            viewModel.resetState()

        }
    }

    WelcomeScreenContent(
        modifier = Modifier.fillMaxSize(),
        navigateToSignUp = navigateToSignUp,
        authenticateWithGoogle = authenticateWithGoogle
    )
}
