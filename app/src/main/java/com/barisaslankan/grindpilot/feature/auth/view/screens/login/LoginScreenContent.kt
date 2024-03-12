package com.barisaslankan.grindpilot.feature.auth.view.screens.login

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text2.BasicTextField2
import androidx.compose.foundation.text2.input.rememberTextFieldState
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.barisaslankan.grindpilot.R
import com.barisaslankan.grindpilot.ui.theme.BackgroundColor
import com.barisaslankan.grindpilot.ui.theme.OrangeGP
import com.barisaslankan.grindpilot.ui.theme.Typography

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LoginScreenContent(
    modifier : Modifier,
    email : String,
    password : String,
    onEmailChanged : (String) -> Unit,
    onPasswordChanged : (String) -> Unit,
    onSignInClicked : () -> Unit,
    navigateToSignUp : () -> Unit
){

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Image(
            painter = painterResource(id = R.drawable.grind_pilot_logo),
            contentDescription = "App Logo"
        )

        OutlinedTextField(
            value = email,
            onValueChange = onEmailChanged,
            label = {Text("Email")},
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = password,
            onValueChange = onPasswordChanged,
            label = { Text(text = "Password") },
            shape = RoundedCornerShape(16.dp),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier.fillMaxWidth()
        )

        ElevatedButton(
            modifier = Modifier
                .background(OrangeGP)
                .clip(RoundedCornerShape(16.dp)),
            onClick = onSignInClicked
        ){
            Text(
                text = "Sign In",
                style = TextStyle(
                    color = BackgroundColor,
                    fontStyle = Typography.titleLarge.fontStyle
                )
            )
        }

        Text(
            modifier = Modifier.clickable {navigateToSignUp()},
            text = "Don't have an account?",
            style = TextStyle.Default.copy(
                color = MaterialTheme.colorScheme.tertiary,
                fontStyle = Typography.bodyMedium.fontStyle
            )
        )
    }
}