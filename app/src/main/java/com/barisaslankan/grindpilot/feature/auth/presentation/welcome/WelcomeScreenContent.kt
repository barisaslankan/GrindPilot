package com.barisaslankan.grindpilot.feature.auth.presentation.welcome

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.barisaslankan.grindpilot.R
import com.barisaslankan.grindpilot.ui.theme.BackgroundColor
import com.barisaslankan.grindpilot.ui.theme.OrangeGP

@Composable
fun WelcomeScreenContent(
    modifier: Modifier,
    navigateToSignUp : () -> Unit,
    authenticateWithGoogle : () -> Unit
){

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Spacer(
            modifier = Modifier.padding(top=16.dp)
        )

        Image(
            painter = painterResource(
                id = R.drawable.grind_pilot_logo
            ),
            contentDescription = "App Logo",
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .padding(8.dp)
        )

        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 60.dp),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            OutlinedButton(
                onClick = navigateToSignUp,
                modifier= Modifier.size(80.dp),  //avoid the oval shape
                shape = CircleShape,
                border= BorderStroke(1.5.dp, Color.Gray),
                contentPadding = PaddingValues(1.5.dp),  //avoid the little icon
                colors = ButtonDefaults.outlinedButtonColors(contentColor =  Color.Gray)
            ) {
                Icon(
                    imageVector = Icons.Default.MailOutline,
                    tint = OrangeGP,
                    contentDescription = "Sign Up with Email Button",
                    modifier = Modifier.size(56.dp),
                    )
            }
            OutlinedButton(
                onClick = authenticateWithGoogle,
                modifier= Modifier.size(80.dp),
                shape = CircleShape,
                border= BorderStroke(1.5.dp, Color.Gray),
                contentPadding = PaddingValues(1.5.dp),
                colors = ButtonDefaults.outlinedButtonColors(contentColor =  Color.Gray)
            ) {
                Image(
                    modifier = Modifier.fillMaxSize().clip(CircleShape),
                    painter = painterResource(id = R.drawable.android_login),
                    contentDescription = "content description")
            }
            OutlinedButton(
                onClick = { /*facebook login*/ },
                elevation = ButtonDefaults.buttonElevation(125.dp),
                modifier= Modifier.size(80.dp),
                shape = CircleShape,
                border= BorderStroke(1.5.dp, Color.Gray),
                contentPadding = PaddingValues(1.5.dp),
                colors = ButtonDefaults.outlinedButtonColors(contentColor =  Color.Gray)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.facebook_logo_primary), contentDescription = "email ")
            }
        }
    }
}

@Preview
@Composable
fun WelcomeScreenPreview() {
    WelcomeScreenContent(
        modifier = Modifier,
        authenticateWithGoogle = { /*TODO*/ },
        navigateToSignUp = { /*TODO*/ }
    )
}