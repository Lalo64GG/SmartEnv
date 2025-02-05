package com.example.smartenv.home.presentation.components

import android.health.connect.datatypes.units.Temperature
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp

@Composable
fun TemperatureGraph(modifier: Modifier = Modifier, temperature: Float) {
    // Usamos remember para mantener el estado de las temperaturas
    val temperatures = remember { mutableStateListOf<Float>() }

    // Agregar la nueva temperatura cuando cambia el parámetro
    LaunchedEffect(temperature) {
        temperatures.add(temperature)
    }

    Canvas(modifier = modifier.padding(horizontal = 16.dp)) {
        if (temperatures.isEmpty()) return@Canvas

        val maxTemp = temperatures.maxOrNull() ?: 30f
        val minTemp = temperatures.minOrNull() ?: 15f
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
