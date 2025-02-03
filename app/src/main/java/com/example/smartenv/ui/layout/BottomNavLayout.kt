package com.example.smartenv.ui.layout

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.smartenv.shared.components.BottomNavigationBar

@Composable
fun BottomNavLayout(navController: NavController, content: @Composable (Modifier) -> Unit) {
    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) { innerPadding ->
        content(Modifier.padding(innerPadding))
    }
}
