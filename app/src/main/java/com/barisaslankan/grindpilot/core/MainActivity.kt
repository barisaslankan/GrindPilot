package com.barisaslankan.grindpilot.core

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.barisaslankan.grindpilot.feature_auth.presentation.welcome.GoogleAuthUiClient
import com.barisaslankan.grindpilot.feature_auth.presentation.welcome.WelcomeViewModel
import com.barisaslankan.grindpilot.core.navigation.Screen
import com.barisaslankan.grindpilot.core.navigation.SetUpNavGraph
import com.barisaslankan.grindpilot.core.ui.theme.GrindPilotTheme
import com.google.android.gms.auth.api.identity.Identity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GrindPilotTheme {
                val navController = rememberNavController()
                val welcomeViewModel : WelcomeViewModel = hiltViewModel()

                val googleAuthUiClient by lazy {
                    GoogleAuthUiClient(
                        oneTapClient = Identity.getSignInClient(applicationContext)
                    )
                }

                val launcher = rememberLauncherForActivityResult(
                    contract = ActivityResultContracts.StartIntentSenderForResult(),
                    onResult = {result ->
                        if(result.resultCode == RESULT_OK) {
                            lifecycleScope.launch {
                                val signInResult = googleAuthUiClient.signInWithIntent(
                                    intent = result.data ?: return@launch
                                )
                                welcomeViewModel.onSignInResult(signInResult)
                            }
                        }
                    }
                )

                SetUpNavGraph(
                    welcomeViewModel = welcomeViewModel,
                    authenticateWithGoogle = {
                        lifecycleScope.launch {
                            val signInIntentSender = googleAuthUiClient.signIn()
                            launcher.launch(
                                IntentSenderRequest.Builder(
                                    signInIntentSender ?: return@launch
                                ).build()
                            )

                        }
                    },
                    startDestination = Screen.WelcomeScreen.route,
                    navController = navController
                )
            }
            }
        }
    }

