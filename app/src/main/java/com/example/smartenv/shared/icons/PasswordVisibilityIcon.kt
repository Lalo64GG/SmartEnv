package com.example.smartenv.shared.icons

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable

@Composable
fun PasswordVisibilityIcon(
    isPasswordVisible: Boolean,
    onToggleVisibility: () -> Unit
) {
    IconButton(onClick = onToggleVisibility) {
        Icon(
            imageVector = if (isPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
            contentDescription = if (isPasswordVisible) "Ocultar contraseña" else "Mostrar contraseña"
        )
    }
}