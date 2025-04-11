package com.example.smartenv.home.presentation

import android.content.Context
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.smartenv.home.presentation.components.AirQualityGraph
import com.example.smartenv.home.presentation.components.GraphCard
import com.example.smartenv.home.presentation.components.TemperatureGraph
import com.example.smartenv.ui.theme.Gradient1
import kotlinx.coroutines.delay

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        while (true) {
            homeViewModel.getRecord()
            delay(3000)
        }
    }

    val context = LocalContext.current // Obtener el contexto aquí

    SideEffect {
        homeViewModel.vibratePhone(context, 3000) // Pasar el contexto válido
    }

    val record by homeViewModel.record.observeAsState()

    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Bienvenido a SmartEnv",
            color = Color.Black,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
        )

        GraphCard(title = "Temperatura") {
            TemperatureGraph(modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
                temperature = record?.temperature?.toFloat() ?: 0.0f
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        GraphCard (title = "Calidad del Aire") {
            AirQualityGraph(modifier = Modifier
                .fillMaxWidth()
                .height(200.dp))
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = { navController.navigate("profile") },
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .padding(8.dp)
                .height(50.dp)
        ) {
            Text(text = "Empezar", color = Color(0xFFffac44), fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }



    }
}
