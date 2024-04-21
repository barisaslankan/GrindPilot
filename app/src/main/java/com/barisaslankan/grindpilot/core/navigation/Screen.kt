package com.barisaslankan.grindpilot.core.navigation

sealed class Screen(val route: String) {
    data object WelcomeScreen : Screen(route = "welcome_screen")
    data object SignUpScreen : Screen(route = "sign_up_screen")
    data object LoginScreen : Screen(route = "login_screen")
    data object CalendarScreen : Screen(route = "calendar_screen")
    data object CreatePlanScreen : Screen(route = "create_plan_screen")
    data object PlansScreen : Screen(route = "plans_screen")
    data object SetGoalScreen : Screen(route = "set_goal_screen")
}