package com.barisaslankan.grindpilot.feature.auth.view.screens.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.barisaslankan.grindpilot.R
import com.barisaslankan.grindpilot.ui.theme.OrangeGP
import com.barisaslankan.grindpilot.ui.theme.Typography

@Composable
fun SignUpScreenContent(
    modifier : Modifier,
    email : String,
    password : String,
    repeatPassword : String,
    onSignUpClicked : () -> Unit,
    onEmailTextChanged : (String) -> Unit,
    onPasswordTextChanged : (String) -> Unit,
    onRepeatPasswordTextChanged : (String) -> Unit,
    navigateToLogin : () -> Unit
){

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally, 
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        
        Image(
            modifier = Modifier.fillMaxWidth(),
            painter = painterResource(id = R.drawable.grind_pilot_logo),
            contentDescription = "App Logo"
        )

        OutlinedTextField(
            value = email,
            onValueChange = onEmailTextChanged,
            label = { Text(text = "Email")},
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = password,
            onValueChange = onPasswordTextChanged,
            label = { Text(text = "Password") },
            shape = RoundedCornerShape(16.dp),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = repeatPassword,
            onValueChange = onRepeatPasswordTextChanged,
            label = { Text(text = "Repeat Password") },
            shape = RoundedCornerShape(16.dp),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedButton(
            modifier = Modifier
                .background(OrangeGP)
                .clip(RoundedCornerShape(16.dp)),
            onClick = onSignUpClicked
        ){
            Text(
                text = "Sign Up",
                style = TextStyle(
                    color = Color.Black,
                    fontStyle = Typography.titleLarge.fontStyle
                )
            )
        }

        Text(
            modifier = Modifier.clickable {navigateToLogin()},
            text = "Already have an account?",
            style = TextStyle.Default.copy(
                color = MaterialTheme.colorScheme.tertiary,
                fontStyle = Typography.bodyMedium.fontStyle
            )
        )
    }
}
