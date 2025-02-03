package com.example.smartenv.register.presentation.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.smartenv.shared.components.CustomButton
import com.example.smartenv.shared.components.CustomTextField
import com.example.smartenv.shared.icons.PasswordVisibilityIcon
import com.example.smartenv.ui.theme.Gradient1
import com.example.smartenv.ui.theme.RoundedModifier
import com.example.smartenv.ui.theme.noBorderTextFieldColors

@Composable
fun RegisterContent(
    email: String,
    password: String,
    username: String,
    isPasswordVisible: Boolean,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onUsernameChange: (String) -> Unit,
    onTogglePasswordVisibility: () -> Unit,
    validateEmail: () -> Unit,
    createAccount: () -> Unit
){

    CustomTextField(
        value = email,
        onValueChange =  onEmailChange,
        label = "Email",
        type = KeyboardType.Email,
        leadingIcon = { Icon(Icons.Default.Email, contentDescription = "Email Icon") },
        colors = noBorderTextFieldColors(),
        placeholder = "example@gmail.com",
        modifier = Modifier.onFocusChanged {  isFocusable ->
            if(!isFocusable.hasFocus){
                Log.d("Email", "Email: $email")
                validateEmail()
            }

        }.then(RoundedModifier())
    )

    Spacer(Modifier.height(15.dp))

    CustomTextField(
        value = username,
        onValueChange = onUsernameChange,
        label = "Username",
        placeholder = "JohnDoe",
        colors = noBorderTextFieldColors(),
        leadingIcon = { Icon(Icons.Default.Person, contentDescription = "Person Icon") },
        modifier = RoundedModifier()
    )

    Spacer(Modifier.height(15.dp))

    CustomTextField(
        value = password,
        onValueChange = onPasswordChange,
        label = "Password",
        type = KeyboardType.Password,
        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        placeholder = "*******",
        leadingIcon = { Icon(Icons.Default.Lock, contentDescription = "Lock Icon") },
        colors = noBorderTextFieldColors(),
        trailingIcon = {
            PasswordVisibilityIcon(
                isPasswordVisible = isPasswordVisible,
                onToggleVisibility = onTogglePasswordVisibility
            )
        },
        modifier = RoundedModifier()
    )

    Spacer(modifier = Modifier.height(80.dp))


    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(60))
            .background(Gradient1)
    ) {
        CustomButton(
            onClick = {
              createAccount()
            },
            text = "Register",
            enabled = email.isNotEmpty() && password.isNotEmpty(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = Color.White,
                disabledContainerColor = Color.Gray,
                disabledContentColor = Color.White
            ),
            modifier = Modifier.fillMaxWidth()
        )
    }

    Spacer(modifier = Modifier.height(80.dp))

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(top = 10.dp)
    ) {
        Text(
            text = "You already have an account?",
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.height(5.dp))
        Text(
            text = "Log in",
            color = Color(0xFFFF8000),
            textAlign = TextAlign.Center
        )
    }


}