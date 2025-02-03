package com.example.smartenv.shared.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun EmailValidationMessage(isEmailTaken: Boolean) {
    AnimatedVisibility(
        visible = isEmailTaken,
        enter = fadeIn() + slideInVertically(initialOffsetY = { -it / 2 }),
        exit = fadeOut() + slideOutVertically(targetOffsetY = { -it / 2 })
    ) {
        Text(
            text = "This email is already taken" ,
            color = Color.Red ,
            fontSize = 16.sp,
            modifier = Modifier.padding(start = 8.dp, top = 4.dp)
        )
    }
}
