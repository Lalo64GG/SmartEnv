package com.example.smartenv.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun noBorderTextFieldColors() = TextFieldDefaults.colors(
    focusedLabelColor = MaterialTheme.colorScheme.primary,
    unfocusedLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
    focusedContainerColor = MaterialTheme.colorScheme.background,
    unfocusedContainerColor = MaterialTheme.colorScheme.background,
    focusedIndicatorColor = MaterialTheme.colorScheme.background,
    unfocusedIndicatorColor = MaterialTheme.colorScheme.background,
    disabledIndicatorColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
)

fun RoundedModifier() = Modifier
    .shadow(
        elevation = 4.dp,
        shape = RoundedCornerShape(50.dp)
    )
    .background(Color.White, RoundedCornerShape(50.dp))