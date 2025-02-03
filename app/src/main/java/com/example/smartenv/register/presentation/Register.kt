package com.example.smartenv.register.presentation

import android.os.Looper
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.smartenv.register.data.model.CreateUserRequest
import com.example.smartenv.register.presentation.components.RegisterContent
import com.example.smartenv.shared.components.EmailValidationMessage
import com.example.smartenv.shared.components.ToastNotification
import com.example.smartenv.shared.components.ToastType
import com.example.smartenv.ui.theme.Gradient1
import android.os.Handler

@Composable
fun RegisterScreen(
    navController: NavController,
    registerViewModel: RegisterViewModel = hiltViewModel()
) {
    val email: String by registerViewModel.email.observeAsState("")
    val username: String by registerViewModel.username.observeAsState("")
    val password: String by registerViewModel.password.observeAsState("")
    var isPasswordVisible by remember { mutableStateOf(false) }
    val isEmailValid by registerViewModel.isEmailValid.observeAsState(false)
    var toastMessage by remember { mutableStateOf("") }
    var toastType by remember { mutableStateOf(ToastType.SUCCESS) }
    var showToast by remember { mutableStateOf(false) }

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
                .background(Gradient1)
        ) {
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
            EmailValidationMessage(isEmailValid)
            RegisterContent(
                email = email,
                password = password,
                username = username,
                isPasswordVisible = isPasswordVisible,
                onEmailChange = { registerViewModel.onChangeEmail(it) },
                onPasswordChange = { registerViewModel.onChangePassword(it) },
                onUsernameChange = { registerViewModel.onChangeUser(it) },
                onTogglePasswordVisibility = { isPasswordVisible = !isPasswordVisible },
                validateEmail = { registerViewModel.validateEmail() },
                createAccount = {
                    if (email.isNotEmpty() && password.isNotEmpty() && username.isNotEmpty()) {
                        val request = CreateUserRequest(email, username, password)
                        registerViewModel.createAccount(request) { success, message ->
                            toastMessage = message
                            toastType = if (success) ToastType.SUCCESS else ToastType.ERROR
                            showToast = true
                            if(success){
                                Handler(Looper.getMainLooper()).postDelayed({
                                    navController.navigate("login")
                                }, 3000)
                            }
                        }
                    }
                }
            )
        }
    }

    if (showToast) {
        ToastNotification(
            message = toastMessage,
            type = toastType,
            onDismiss = { showToast = false }
        )
    }
}

