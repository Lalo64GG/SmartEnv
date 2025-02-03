package com.example.smartenv.shared.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val customButtonModifier = Modifier
    .fillMaxWidth()
    .height(50.dp)

@Composable
fun CustomButton(
    onClick: () -> Unit,
    text: String,
    enabled: Boolean = true,
    colors: ButtonColors = defaultButtonColors(),
    modifier: Modifier = Modifier
){
    Button(
        onClick = onClick,
        enabled = enabled,
        colors = colors,
        modifier = modifier.then(customButtonModifier)
    ) {
        Text(
            text = text,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun defaultButtonColors(): ButtonColors{
    return ButtonDefaults.buttonColors(
        containerColor = MaterialTheme.colorScheme.onBackground,
        contentColor = MaterialTheme.colorScheme.background
    )

}