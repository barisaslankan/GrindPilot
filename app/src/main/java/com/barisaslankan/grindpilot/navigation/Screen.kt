package com.barisaslankan.grindpilot.navigation

sealed class Screen(val route: String) {
    data object WelcomeScreen : Screen(route = "welcome_screen")
    data object SignUpScreen : Screen(route = "sign_up_screen")
    data object LoginScreen : Screen(route = "login_screen")
}