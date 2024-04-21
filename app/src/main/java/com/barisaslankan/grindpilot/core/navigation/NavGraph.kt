package com.barisaslankan.grindpilot.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.barisaslankan.grindpilot.feature.auth.presentation.login.LoginScreen
import com.barisaslankan.grindpilot.feature.auth.presentation.signup.SignUpScreen
import com.barisaslankan.grindpilot.feature.auth.presentation.welcome.WelcomeScreen
import com.barisaslankan.grindpilot.feature.auth.presentation.welcome.WelcomeViewModel
import com.barisaslankan.grindpilot.feature.calendar.presentation.CalendarScreen
import com.barisaslankan.grindpilot.feature.planning.presentation.create_plan.CreatePlanScreen
import com.barisaslankan.grindpilot.feature.planning.presentation.plans.PlansScreen
import com.barisaslankan.grindpilot.feature.set_goal.presentation.SetGoalScreen

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
            navigateToCalendar = {
                navController.navigate(Screen.CalendarScreen.route)
            }
        )
        loginRoute(
            navigateToCalendar = {
                navController.navigate(Screen.CalendarScreen.route )},
            navigateToSignUp = {
                navController.navigate(Screen.SignUpScreen.route)
            }
        )
        signUpRoute(
            navigateToCalendar = {navController.navigate(Screen.CalendarScreen.route) },
            navigateToLogin = {
                navController.navigate(Screen.LoginScreen.route)
            }
        )
        calendarRoute(
            navigateToSetGoal = {
                navController.navigate(Screen.SetGoalScreen.route)
            },
            navigateToPlans = {
                navController.navigate(Screen.PlansScreen.route)
            }
        )
        createPlanRoute(
            navigateToCalendar = {
                navController.navigate(Screen.CalendarScreen.route)
            },
            onBackButtonClicked = {
                navController.popBackStack()
            }
        )
        plansRoute(
            navigateToCreatePlan = {
            navController.navigate(Screen.CreatePlanScreen.route)
        },
            onBackButtonClicked = {
                navController.popBackStack()
            }
        )
        setGoalRoute(
            onBackPressed = {
                navController.popBackStack()
            }
        )
    }
}

fun NavGraphBuilder.welcomeRoute(
    welcomeViewModel : WelcomeViewModel,
    authenticateWithGoogle : () -> Unit,
    navigateToCalendar : () -> Unit,
    navigateToSignUp : () -> Unit
){
    composable(route = Screen.WelcomeScreen.route){

        WelcomeScreen(
            viewModel = welcomeViewModel,
            authenticateWithGoogle = authenticateWithGoogle,
            navigateToCalendar = navigateToCalendar,
            navigateToSignUp = navigateToSignUp

        )
    }
}

fun NavGraphBuilder.loginRoute(
    navigateToSignUp: () -> Unit,
    navigateToCalendar: () -> Unit
){

    composable(route = Screen.LoginScreen.route){

        LoginScreen(
            navigateToSignUp = navigateToSignUp,
            navigateToCalendar = navigateToCalendar
        )

    }
}

fun NavGraphBuilder.signUpRoute(
    navigateToCalendar: () -> Unit,
    navigateToLogin : () -> Unit
){

    composable(route = Screen.SignUpScreen.route){

        SignUpScreen(
            navigateToLogin = navigateToLogin,
            navigateToCalendar = navigateToCalendar
        )
    }
}
fun NavGraphBuilder.calendarRoute(
    navigateToSetGoal: () -> Unit,
    navigateToPlans : () -> Unit
){

    composable(route = Screen.CalendarScreen.route){

        CalendarScreen(
            navigateToSetGoal = navigateToSetGoal,
            navigateToPlans = navigateToPlans
        )

    }

}

fun NavGraphBuilder.createPlanRoute(
    onBackButtonClicked: () -> Unit,
    navigateToCalendar: () -> Unit
){
    composable(route = Screen.CreatePlanScreen.route){

        CreatePlanScreen(
            onBackButtonClicked = onBackButtonClicked,
            navigateToCalendar = navigateToCalendar
        )
    }

}

fun NavGraphBuilder.plansRoute(
    navigateToCreatePlan : () -> Unit,
    onBackButtonClicked : () -> Unit
){
    composable(route = Screen.PlansScreen.route){

        PlansScreen(
            navigateToCreatePlan = navigateToCreatePlan,
            onBackButtonClicked = onBackButtonClicked
        )

    }
}

fun NavGraphBuilder.setGoalRoute(
    onBackPressed : () -> Unit
){
    composable(route = Screen.SetGoalScreen.route){
        SetGoalScreen(
            onBackPressed = onBackPressed
        )
    }
}