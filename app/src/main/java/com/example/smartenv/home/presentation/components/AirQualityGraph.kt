package com.example.smartenv.home.presentation.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AirQualityGraph(modifier: Modifier = Modifier) {
    val airQualityLevels = listOf(30, 45, 60, 50, 70, 85, 90)
    Canvas(modifier = modifier.padding(horizontal = 16.dp)) {
        val maxAQ = 100f
        val stepX = size.width / airQualityLevels.size
        val stepY = size.height / maxAQ

        airQualityLevels.forEachIndexed { index, level ->
            drawRect(
                color = if (level > 70) Color.Red else Color.Green,
                topLeft = androidx.compose.ui.geometry.Offset(index * stepX, size.height - (level * stepY)),
                size = androidx.compose.ui.geometry.Size(stepX * 0.8f, level * stepY)
            )
        }
    }
}