package com.example.smartenv.login.presentation

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.smartenv.login.data.model.LoginUserRequest
import com.example.smartenv.login.presentation.components.LoginContent
import com.example.smartenv.ui.theme.Gradient1

@Composable
fun LoginScreen(
    navController: NavController,
    loginViewModel: LoginViewModel = hiltViewModel()
) {

    val email by loginViewModel.email.observeAsState("")
    val password by loginViewModel.password.observeAsState("")
    var isPasswordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Top
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .clip(shape = RoundedCornerShape(bottomStart = 100.dp))
                .background(Gradient1)
        ) {
            Box(
                modifier = Modifier.align(Alignment.Center)
            ) {
                Text(
                    text = "Hi There!\n\nPlease Log In",
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 40.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            LoginContent(
                email = email,
                password = password,
                isPasswordVisible = isPasswordVisible,
                onEmailChange = { loginViewModel.onChangeEmail(it) },
                onPasswordChange = { loginViewModel.onChangePassword(it) },
                onTogglePasswordVisibility = { isPasswordVisible = !isPasswordVisible },
                onLoginClick = {
                    if (email.isNotEmpty() && password.isNotEmpty()) {
                            if (true) {
                                Log.d("Login", "${true}")
                                navController.navigate("home")
                            }
                    }
                },
                navController = navController
            )
        }
    }
}