package com.example.smartenv.register.presentation

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
import com.example.smartenv.register.presentation.components.RegisterContent
import com.example.smartenv.ui.theme.Gradient1


@Composable
fun RegisterScreen(
    registerViewModel: RegisterViewModel = hiltViewModel()
) {
    val email: String by registerViewModel.email.observeAsState("")
    val username: String by registerViewModel.username.observeAsState("")
    val password: String by registerViewModel.password.observeAsState("")
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
                .clip(shape = RoundedCornerShape(bottomEnd = 100.dp))
                .background(
                    brush = Brush.verticalGradient(
                        colors = Gradient1
                    )
                )
        ){
            Box(
                modifier = Modifier.align(Alignment.Center)
            ){
                Column(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(horizontal = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    Text(
                        text = "Welcome",
                        textAlign = TextAlign.Center,
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 40.sp,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    Text(
                        text = "Please create an account to continue",
                        textAlign = TextAlign.Center,
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            RegisterContent(
                email = email,
                password = password,
                username = username,
                isPasswordVisible = isPasswordVisible,
                onEmailChange = { registerViewModel.onChangeEmail(it) },
                onPasswordChange = { registerViewModel.onChangePassword(it) },
                onUsernameChange = { registerViewModel.onChangeUser(it) },
                onTogglePasswordVisibility = { isPasswordVisible = !isPasswordVisible },
            )
        }
    }
}