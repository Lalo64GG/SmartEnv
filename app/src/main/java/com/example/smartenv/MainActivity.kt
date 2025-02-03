package com.example.smartenv

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.smartenv.login.presentation.LoginScreen
import com.example.smartenv.register.presentation.RegisterScreen
import com.example.smartenv.ui.layout.BottomNavLayout
import com.example.smartenv.ui.theme.SmartEnvTheme
import com.example.smartenv.home.presentation.HomeScreen
import com.example.smartenv.profile.presentation.ProfileScreen
import com.example.smartenv.settings.presentation.SettingsScreen
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
                    composable("login") { LoginScreen(navController) }
                    composable("register") { RegisterScreen(navController) }

                    composable("home") {
                        BottomNavLayout(navController) { modifier ->
                            HomeScreen(modifier, navController)
                        }
                    }
                    composable("profile") {
                        BottomNavLayout(navController) { modifier ->
                            ProfileScreen(modifier, navController)
                        }
                    }
                    composable("settings") {
                        BottomNavLayout(navController) { modifier ->
                            SettingsScreen(modifier, navController)
                        }
                    }
                }
            }
        }
    }
}
