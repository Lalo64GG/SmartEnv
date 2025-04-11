package com.example.smartenv.profile.presentation

import android.Manifest
import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.FileProvider
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.smartenv.R
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    profileViewModel: ProfileViewModel = hiltViewModel()
) {
    val user by profileViewModel.userData.collectAsState(initial = null)
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val context = LocalContext.current
    val username by profileViewModel.username.observeAsState("")

    // Estado para controlar el diálogo de selección de imagen
    var showImagePickerDialog by remember { mutableStateOf(false) }

    // Launcher para seleccionar imagen de la galería
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageUri = uri
    }

    // Para guardar la URI de la foto tomada por la cámara
    var tempCameraUri by remember { mutableStateOf<Uri?>(null) }

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) { success ->
        if (success) {
            imageUri = tempCameraUri
        }
    }

    // Launcher para solicitar permiso de cámara
    // Reemplaza el código dentro del launcher para solicitar permiso de cámara con este:

    val cameraPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            try {
                // Crear archivo temporal para la foto
                val file = File(
                    context.getExternalFilesDir(null),
                    "temp_photo_${System.currentTimeMillis()}.jpg"
                )

                val uri = FileProvider.getUriForFile(
                    context,
                    context.applicationContext.packageName + ".fileprovider",
                    file
                )

                tempCameraUri = uri
                cameraLauncher.launch(uri)
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(
                    context,
                    "Error al preparar la cámara: ${e.message}",
                    Toast.LENGTH_LONG
                ).show()
            }
        } else {
            Toast.makeText(context, "Permiso de cámara denegado", Toast.LENGTH_SHORT).show()
        }
    }
    // Launcher para tomar foto con la cámara


    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Image(
            painter = if (imageUri != null) {
                rememberAsyncImagePainter(imageUri)
            } else {
                painterResource(id = R.drawable.profile_picture)
            },
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .clickable {
                    showImagePickerDialog = true
                }
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = user?.Username ?: "Usuario desconocido",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Text(
            text = user?.Email ?: "Correo no disponible",
            fontSize = 16.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Campo de texto para editar el nombre de usuario
        OutlinedTextField(
            value = username,
            onValueChange = { profileViewModel.onChangeUsername(it) },
            label = { Text("Nuevo Nombre de Usuario") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botón para guardar el nombre de usuario
        Button(
            onClick = {
                profileViewModel.saveUsername(username) { success, message ->
                    if (success) {
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0072FF)),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.padding(horizontal = 20.dp)
        ) {
            Text(text = "Guardar", color = Color.White, fontSize = 18.sp)
        }

        Spacer(modifier = Modifier.height(30.dp))

        ProfileOption("Configuración", onClick = { navController.navigate("settings") })
        ProfileOption("Cerrar Sesión", onClick = {
            profileViewModel.logout()
            navController.navigate("login")
        },
            containerColor = Color.Red.copy(alpha = 0.5f),
            textColor = Color.White
        )
    }

    // Diálogo para seleccionar entre cámara y galería
    if (showImagePickerDialog) {
        AlertDialog(
            onDismissRequest = { showImagePickerDialog = false },
            title = { Text("Seleccionar imagen de perfil") },
            text = { Text("¿Cómo deseas seleccionar tu imagen de perfil?") },
            confirmButton = {
                Button(
                    onClick = {
                        showImagePickerDialog = false
                        cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
                    }
                ) {
                    Text("Cámara")
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        showImagePickerDialog = false
                        galleryLauncher.launch("image/*")
                    }
                ) {
                    Text("Galería")
                }
            }
        )
    }
}

// Función para crear un archivo de imagen temporal
private fun createImageFile(context: Context): File? {
    val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
    val imageFileName = "JPEG_${timeStamp}_"

    return try {
        File.createTempFile(
            imageFileName,
            ".jpg",
            context.getExternalFilesDir(null)
        )
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

@Composable
fun ProfileOption(text: String, onClick: () -> Unit, containerColor: Color = Color.White, textColor: Color = Color.Black) {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 6.dp)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = containerColor)
    ) {
        Text(
            text = text,
            fontSize = 18.sp,
            color = textColor,
            modifier = Modifier.padding(16.dp)
        )
    }
}