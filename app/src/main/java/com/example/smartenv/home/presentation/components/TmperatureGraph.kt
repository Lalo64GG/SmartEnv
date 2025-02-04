package com.example.smartenv.home.presentation.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp

@Composable
fun TemperatureGraph(modifier: Modifier = Modifier) {
    val temperatures = listOf(20, 22, 24, 23, 26, 28, 27)
    Canvas(modifier = modifier.padding(horizontal = 16.dp)) {
        val maxTemp = temperatures.maxOrNull()?.toFloat() ?: 30f
        val minTemp = temperatures.minOrNull()?.toFloat() ?: 15f
        val stepX = size.width / (temperatures.size - 1)
        val stepY = size.height / (maxTemp - minTemp)

        for (i in 1 until temperatures.size) {
            drawLine(
                color = Color.Yellow,
                start = androidx.compose.ui.geometry.Offset(
                    (i - 1) * stepX,
                    size.height - (temperatures[i - 1] - minTemp) * stepY
                ),
                end = androidx.compose.ui.geometry.Offset(
                    i * stepX,
                    size.height - (temperatures[i] - minTemp) * stepY
                ),
                strokeWidth = 4.dp.toPx(),
                cap = StrokeCap.Round
            )
        }
    }
}