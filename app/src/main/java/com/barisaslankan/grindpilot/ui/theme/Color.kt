package com.barisaslankan.grindpilot.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val OrangeGP = Color(0xFFEFA05B)
val HintColor = Color.Gray
val TextColor
@Composable
get() = if(isSystemInDarkTheme()) Color.White else Color.Black
val BackgroundColor
@Composable
get() = if(isSystemInDarkTheme()) Color.Black else Color.White