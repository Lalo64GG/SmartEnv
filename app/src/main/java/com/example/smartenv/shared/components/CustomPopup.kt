package com.example.smartenv.shared.components

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smartenv.R

enum class PopupType {
    SUCCESS, WARNING, ERROR
}

@Composable
fun CustomPopup(
    message: String,
    type: PopupType,
    onDismiss: () -> Unit
) {
    val backgroundColor = when (type) {
        PopupType.SUCCESS -> Color(0xFF4CAF50)
        PopupType.WARNING -> Color(0xFFFFC107)
        PopupType.ERROR -> Color(0xFFF44336)
    }

    val icon = when (type) {
        PopupType.SUCCESS -> R.drawable.ic_success
        PopupType.WARNING -> R.drawable.ic_warning
        PopupType.ERROR -> R.drawable.ic_error
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.4f))
            .clickable { onDismiss() },
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(backgroundColor)
                .padding(20.dp)
                .width(280.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = "Popup Icon",
                tint = Color.White,
                modifier = Modifier.size(48.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = message,
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "OK",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.Black.copy(alpha = 0.2f))
                    .padding(horizontal = 20.dp, vertical = 8.dp)
                    .clickable { onDismiss() }
            )
        }
    }
}
