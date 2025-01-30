package com.example.smartenv.register.presentation

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun RegisterScreen(
    registerViewModel: RegisterViewModel = hiltViewModel()
) {
    val email:String by registerViewModel.email.observeAsState("")
    val username:String by registerViewModel.username.observeAsState("")
    val password:String by registerViewModel.password.observeAsState("")
    val errorMessage: String by registerViewModel.errorMessage.observeAsState("")
    var isFocused by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
        ,
        verticalArrangement = Arrangement.Center
    ){
        Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            text ="Registro",
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            color = Color.DarkGray
        )
        Spacer(modifier = Modifier.height(30.dp))
        TextField(
            value = email,
            onValueChange = {
                registerViewModel.onChangeEmail(it)
            },
            label = { Text("Email") },
            shape = RoundedCornerShape(10.dp),
            placeholder = { Text("example@gmail.com") },
            leadingIcon = { Icon(
                Icons.Default.Email ,
                contentDescription = "Person Icon",
                modifier = Modifier
                    .animateContentSize { initialValue, targetValue ->  }
                    .size( if (isFocused) 32.dp else 23.dp)
            ) },
            colors = TextFieldDefaults.colors(
                unfocusedLabelColor = Color.DarkGray,
                focusedLabelColor = Color.DarkGray,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.DarkGray,
                unfocusedIndicatorColor = Color.Red,
                disabledIndicatorColor = Color.Blue,
                cursorColor = Color.Blue
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .onFocusChanged { focusState ->
                    if (focusState.hasFocus) {
                        isFocused = focusState.isFocused
                        registerViewModel.validateEmail()
                    }
                }
        )
        Spacer(Modifier.height(10.dp))
        TextField(
            value = username,
            onValueChange = {
                registerViewModel.onChangeUser(it)
            },
            label = { Text("Username") },
            shape = RoundedCornerShape(10.dp),
            placeholder = { Text("example") },
            leadingIcon = { Icon(Icons.Default.Person, contentDescription = "Person Icon") },
            colors = TextFieldDefaults.colors(
                unfocusedLabelColor = Color.DarkGray,
                focusedLabelColor = Color.DarkGray,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.DarkGray,
                unfocusedIndicatorColor = Color.Blue,
                disabledIndicatorColor = Color.Blue,
                cursorColor = Color.Blue
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        )
        Spacer(Modifier.height(10.dp))
        TextField(
            value = password,
            onValueChange = {
                registerViewModel.onChangePassword(it)
            },
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
                disabledIndicatorColor = Color.DarkGray,
                cursorColor = Color.Blue
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = 10.dp)
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.DarkGray,
                contentColor = Color.Black),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(text = "Sign up",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold)
        }

        if (errorMessage.isNotEmpty()) {
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