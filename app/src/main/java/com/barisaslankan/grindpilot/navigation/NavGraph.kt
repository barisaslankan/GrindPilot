package com.barisaslankan.grindpilot.navigation

import android.content.Context
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.barisaslankan.grindpilot.feature.auth.view.screens.login.LoginScreen
import com.barisaslankan.grindpilot.feature.auth.view.screens.login.LoginScreenState
import com.barisaslankan.grindpilot.feature.auth.view.screens.login.LoginViewModel
import com.barisaslankan.grindpilot.feature.auth.view.screens.signup.SignUpScreen
import com.barisaslankan.grindpilot.feature.auth.view.screens.welcome.GoogleAuthUiClient
import com.barisaslankan.grindpilot.feature.auth.view.screens.welcome.WelcomeScreen
import com.barisaslankan.grindpilot.feature.auth.view.screens.welcome.WelcomeScreenState
import com.barisaslankan.grindpilot.feature.auth.view.screens.welcome.WelcomeViewModel
import com.google.android.gms.auth.api.identity.Identity
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch

@Composable
fun SetUpNavGraph(
    welcomeViewModel: WelcomeViewModel,
    authenticateWithGoogle: () -> Unit,
    startDestination : String,
    navController: NavHostController
){

    NavHost(
        navController = navController,
        startDestination = startDestination
    ){
        welcomeRoute(
            welcomeViewModel = welcomeViewModel,
            authenticateWithGoogle = authenticateWithGoogle,
            navigateToSignUp = {
                navController.navigate(Screen.SignUpScreen.route)
            },
            navigateToHome = {}
        )

        loginRoute(
            navigateToHome = {},
            navigateToSignUp = {
                navController.navigate(Screen.SignUpScreen.route)
            }
        )
        signUpRoute(
            navigateToHome = {},
            navigateToLogin = {
                navController.navigate(Screen.LoginScreen.route)
            }
        )
    }
}

fun NavGraphBuilder.welcomeRoute(
    welcomeViewModel : WelcomeViewModel,
    authenticateWithGoogle : () -> Unit,
    navigateToHome : () -> Unit,
    navigateToSignUp : () -> Unit
){
    composable(route = Screen.WelcomeScreen.route){

        WelcomeScreen(
            viewModel = welcomeViewModel,
            authenticateWithGoogle = authenticateWithGoogle,
            navigateToHome = navigateToHome,
            navigateToSignUp = navigateToSignUp
        )

    }

}

fun NavGraphBuilder.loginRoute(
    navigateToSignUp: () -> Unit,
    navigateToHome: () -> Unit
){

    composable(route = Screen.LoginScreen.route){

        LoginScreen(
            navigateToSignUp = navigateToSignUp,
            navigateToHome = navigateToHome
        )

    }
}

fun NavGraphBuilder.signUpRoute(
    navigateToHome: () -> Unit,
    navigateToLogin : () -> Unit
){

    composable(route = Screen.SignUpScreen.route){

        SignUpScreen(
            navigateToLogin = navigateToLogin,
            navigateToHome = navigateToHome
        )
    }
}