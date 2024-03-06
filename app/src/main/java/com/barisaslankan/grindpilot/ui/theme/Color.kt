package com.barisaslankan.grindpilot.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


val OrangeGP = Color(0xFFEFA05B)

val BackgroundColor
@Composable
get() = if(isSystemInDarkTheme()) Color.Black else Color.White