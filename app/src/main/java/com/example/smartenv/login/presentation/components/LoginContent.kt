package com.example.smartenv.login.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Key
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.smartenv.shared.components.CustomButton
import com.example.smartenv.shared.components.CustomTextField
import com.example.smartenv.shared.icons.PasswordVisibilityIcon
import com.example.smartenv.ui.theme.Gradient1
import com.example.smartenv.ui.theme.Purple40
import com.example.smartenv.ui.theme.Purple80

@Composable
fun LoginContent(
    email: String,
    password: String,
    isPasswordVisible: Boolean,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onTogglePasswordVisibility: () -> Unit,
    onLoginClick: () -> Unit,
    navController: NavController,
){
    val customColors = TextFieldDefaults.colors(
        focusedLabelColor = MaterialTheme.colorScheme.primary,
        unfocusedLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
        focusedContainerColor = MaterialTheme.colorScheme.background,
        unfocusedContainerColor =MaterialTheme.colorScheme.background,
        focusedIndicatorColor = MaterialTheme.colorScheme.background,
        unfocusedIndicatorColor = MaterialTheme.colorScheme.background,
        disabledIndicatorColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
    )
    val customModifier = Modifier
        .shadow(
            elevation = 4.dp,
            shape = RoundedCornerShape(50.dp)
        )
        .background(Color.White, RoundedCornerShape(50.dp))

    CustomTextField(
        value = email,
        onValueChange =  onEmailChange,
        label = "Email",
        type = KeyboardType.Email,
        placeholder = "example@gmail.com",
        colors = customColors,
        leadingIcon = { Icon(Icons.Default.Email, contentDescription = "Email Icon") },
        modifier = customModifier
    )
    Spacer(Modifier.height(15.dp))
    CustomTextField(
        value = password,
        onValueChange = onPasswordChange,
        label = "Password",
        type = KeyboardType.Password,
        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        placeholder = "*******",
        colors = customColors,
        modifier = customModifier,
        leadingIcon = { Icon(Icons.Default.Key, contentDescription = "Password Icon") },
        trailingIcon = {
            PasswordVisibilityIcon(
                isPasswordVisible = isPasswordVisible,
                onToggleVisibility = onTogglePasswordVisibility
            )
        }
    )
    Spacer(modifier = Modifier.height(80.dp))

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(60))
            .background(Gradient1)
    ){
        CustomButton(
            onClick = onLoginClick,
            enabled = email.isNotEmpty() && password.isNotEmpty(),
            text = "Log in",
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = Color.White,
                disabledContainerColor = Color.Gray,
                disabledContentColor = Color.White
            )
        )
    }

    Spacer(Modifier.height(80.dp))

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(top = 10.dp)
    ) {
        Text(
            text = "You don't have an account?",
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.width(5.dp))
        Text(
            text = "Register",
            color = Color(0xFFFF8000),
            textAlign = TextAlign.Center,
            modifier = Modifier.clickable {
                navController.navigate("register")
            })
    }
}

