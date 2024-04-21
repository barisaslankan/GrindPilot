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
import com.barisaslankan.grindpilot.core.ui.theme.BackgroundColor
import com.barisaslankan.grindpilot.core.ui.theme.EXTRA_EXTRA_LARGE_PADDING
import com.barisaslankan.grindpilot.core.ui.theme.LARGE_PADDING
import com.barisaslankan.grindpilot.core.ui.theme.MEDIUM_BORDER_WIDTH
import com.barisaslankan.grindpilot.core.ui.theme.MEDIUM_PADDING
import com.barisaslankan.grindpilot.core.ui.theme.OrangeGP
import com.barisaslankan.grindpilot.core.ui.theme.SMALL_PADDING
import com.barisaslankan.grindpilot.core.ui.theme.TextColor

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
            modifier = Modifier.padding(top= LARGE_PADDING)
        )

        Image(
            painter = painterResource(
                id = R.drawable.grind_pilot_logo
            ),
            contentDescription = "App Logo",
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .padding(SMALL_PADDING)
        )

        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = EXTRA_EXTRA_LARGE_PADDING),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            OutlinedButton(
                onClick = navigateToSignUp,
                modifier= Modifier.size(80.dp),
                shape = CircleShape,
                border= BorderStroke(MEDIUM_BORDER_WIDTH, TextColor),
                contentPadding = PaddingValues(1.5.dp),
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
                border= BorderStroke(MEDIUM_BORDER_WIDTH, TextColor),
                contentPadding = PaddingValues(1.5.dp),
            ) {
                Image(
                    modifier = Modifier.fillMaxSize().clip(CircleShape),
                    painter = painterResource(id = R.drawable.android_login),
                    contentDescription = "Google Sign In Button")
            }
            OutlinedButton(
                onClick = { /*facebook login*/ },
                modifier= Modifier.size(80.dp),
                shape = CircleShape,
                border= BorderStroke(MEDIUM_BORDER_WIDTH, Color.Gray),
                contentPadding = PaddingValues(1.5.dp),
            ) {
                Image(
                    painter = painterResource(id = R.drawable.facebook_logo_primary), contentDescription = "Facebook Sign In Button")
            }
        }
    }
}

@Preview
@Composable
fun WelcomeScreenPreview() {
    WelcomeScreenContent(
        modifier = Modifier,
        authenticateWithGoogle = {},
        navigateToSignUp = {}
    )
}