package com.barisaslankan.grindpilot.feature.auth.presentation.signup

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonColors
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.barisaslankan.grindpilot.R
import com.barisaslankan.grindpilot.ui.theme.BackgroundColor
import com.barisaslankan.grindpilot.ui.theme.OrangeGP
import com.barisaslankan.grindpilot.ui.theme.TextColor
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


    Box(
        modifier
            .fillMaxSize()
            .background(BackgroundColor)
    ) {
        Column(
            modifier = modifier
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            Image(
                modifier = modifier.weight(1f),
                painter = painterResource(id = R.drawable.grind_pilot_logo),
                contentDescription = "App Logo"
            )

            Column(
                modifier = modifier.padding(bottom = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                OutlinedTextField(
                    value = email,
                    textStyle = TextStyle(
                        fontStyle = Typography.bodyLarge.fontStyle,
                        color = TextColor
                    ),
                    onValueChange = onEmailTextChanged,
                    label = { Text(text = "Email") },
                    shape = RoundedCornerShape(16.dp),
                    modifier = modifier
                )

                OutlinedTextField(
                    value = password,
                    textStyle = TextStyle(
                        fontStyle = Typography.bodyLarge.fontStyle,
                        color = TextColor
                    ),
                    onValueChange = onPasswordTextChanged,
                    visualTransformation = PasswordVisualTransformation(),
                    label = { Text(text = "Password") },
                    shape = RoundedCornerShape(16.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),
                    modifier = modifier
                )

                OutlinedTextField(
                    value = repeatPassword,
                    onValueChange = onRepeatPasswordTextChanged,
                    visualTransformation = PasswordVisualTransformation(),
                    label = { Text(text = "Repeat Password") },
                    shape = RoundedCornerShape(16.dp),
                    textStyle = TextStyle(
                        fontStyle = Typography.bodyLarge.fontStyle,
                        color = TextColor
                    ),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),
                    modifier = modifier
                )

                OutlinedButton(
                    modifier = modifier
                        .clip(RoundedCornerShape(16.dp)),
                    border = null,
                    colors = ButtonColors(
                        contentColor = BackgroundColor,
                        containerColor = OrangeGP,
                        disabledContainerColor = OrangeGP,
                        disabledContentColor = BackgroundColor
                    ),
                    onClick = onSignUpClicked
                ) {
                    Text(
                        text = "Sign Up",
                        style = TextStyle(
                            fontStyle = Typography.titleLarge.fontStyle
                        )
                    )
                }

                Text(
                    modifier = Modifier.align(Alignment.CenterHorizontally).clickable { navigateToLogin() },
                    text = "Already have an account?",
                    style = TextStyle.Default.copy(
                        color = OrangeGP,
                        fontStyle = Typography.bodyMedium.fontStyle
                    )
                )

            }
        }
    }
}

@Preview
@Composable
fun SignUpScreenContentPreview(){

    SignUpScreenContent(
        modifier = Modifier.fillMaxWidth(),
        email = "asdasd",
        password = "asdasd",
        repeatPassword = "asdasdad",
        onSignUpClicked = { /*TODO*/ },
        onEmailTextChanged = {},
        onPasswordTextChanged = {},
        onRepeatPasswordTextChanged = {}
    ) {
        
    }

}
