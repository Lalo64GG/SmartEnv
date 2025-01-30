package com.example.smartenv

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.smartenv.Login.data.repository.LoginService
import com.example.smartenv.Login.presentation.LoginScreen
import com.example.smartenv.Login.presentation.LoginViewModel
import com.example.smartenv.register.data.repository.RegisterService
import com.example.smartenv.register.presentation.RegisterScreen
import com.example.smartenv.register.presentation.RegisterViewModel
import com.example.smartenv.ui.theme.SmartEnvTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SmartEnvTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "login") {
                    composable("register") { RegisterScreen() }
                    composable("login") { LoginScreen(navController) }
                }
            }
            }
        }
}