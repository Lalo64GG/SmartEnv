package com.example.smartenv.Login.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.smartenv.Login.data.model.LoginUserRequest

@Composable
fun LoginScreen(
    navController: NavController,
    loginViewModel: LoginViewModel = hiltViewModel()
) {
    val email : String by loginViewModel.email.observeAsState("")
    val password : String by loginViewModel.password.observeAsState("")
    val errorMessage : String by loginViewModel.errorMessage.observeAsState("")
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
        ,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Inicio de sesión",
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            color = Color.DarkGray
        )
        Spacer(Modifier.height(30.dp))
        TextField(
            value = email,
            onValueChange = { loginViewModel.onChangeEmail(it) },
            label = { Text("Email") },
            shape = RoundedCornerShape(10.dp),
            placeholder = { Text("example@gmail.com") },
            leadingIcon = { Icon(Icons.Default.Email , contentDescription = "Person Icon") },
            colors = TextFieldDefaults.colors(
                unfocusedLabelColor = Color.DarkGray,
                focusedLabelColor = Color.DarkGray,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Blue,
                unfocusedIndicatorColor = Color.Blue,
                disabledIndicatorColor = Color.Transparent,
                cursorColor = Color.Transparent
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        )
        Spacer(Modifier.height(10.dp))
        TextField(
            value = password,
            onValueChange = { loginViewModel.onChangePassword(it) },
            label = { Text("Password") },
            shape = RoundedCornerShape(10.dp),
            placeholder = { Text("*******") },
            leadingIcon = { Icon(Icons.Default.Lock, contentDescription = "Lock Icon")},
            colors = TextFieldDefaults.colors(
                unfocusedLabelColor = Color.DarkGray,
                focusedLabelColor = Color.DarkGray,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Blue,
                unfocusedIndicatorColor = Color.Blue,
                disabledIndicatorColor = Color.Transparent,
                cursorColor = Color.Transparent
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                navController.navigate("register")
            },
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = 10.dp)
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.DarkGray,
                contentColor = Color.Black),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(text = "Sign up",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold)
        }

        Spacer(Modifier.height(30.dp))

        Button(
            onClick = {
//                val request = LoginUserRequest(email, password)
//                loginViewModel.loginUser(request)
            },
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = 10.dp)
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.DarkGray,
                contentColor = Color.Black),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(
                text = "Sign In",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }

        if (errorMessage.isNotEmpty()){
            Text(
                text = errorMessage,
                color = Color.Red,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
                    .background(Color(0x30FF0000), shape = RoundedCornerShape(5.dp))
                    .padding(8.dp),
                textAlign = TextAlign.Center
            )
        }
    }


}