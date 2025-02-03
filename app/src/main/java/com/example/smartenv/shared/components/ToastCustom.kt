package com.example.smartenv.shared.components

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smartenv.R
import kotlinx.coroutines.delay

enum class ToastType {
    SUCCESS, WARNING, ERROR
}

@Composable
fun ToastNotification(
    message: String,
    type: ToastType,
    onDismiss: () -> Unit
) {
    var isVisible by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        delay(3000)
        isVisible = false
        delay(500)
        onDismiss()
    }

    val backgroundColor = when (type) {
        ToastType.SUCCESS -> Color(0xFF4CAF50)
        ToastType.WARNING -> Color(0xFFFFC107)
        ToastType.ERROR -> Color(0xFFF44336)
    }

    val icon = when (type) {
        ToastType.SUCCESS -> R.drawable.ic_success
        ToastType.WARNING -> R.drawable.ic_warning
        ToastType.ERROR -> R.drawable.ic_error
    }

    AnimatedVisibility(
        visible = isVisible,
        enter = fadeIn() + slideInVertically(initialOffsetY = { it / 2 }),
        exit = fadeOut() + slideOutVertically(targetOffsetY = { -it / 2 })
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(backgroundColor)
                .clickable { isVisible = false },
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier.padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = "Toast Icon",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = message,
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
