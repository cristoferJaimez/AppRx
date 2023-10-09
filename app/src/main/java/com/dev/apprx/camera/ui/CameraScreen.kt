package com.dev.apprx.camera.ui

import android.Manifest
import android.view.ViewGroup
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.with
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.CalendarViewWeek
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material.icons.filled.ViewList
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Switch
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun CameraScreen(viewModel: CameraViewModel, navController: NavHostController) {
    val showDialog by viewModel.showDialog.observeAsState(false)

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Body(Modifier.align(Alignment.Center), navController)
        FloatingActionButtons(viewModel)
        // Llama al composable del diálogo personalizado
        CustomDialog(showDialog) {
            viewModel.closeCustomDialog()
        }
    }
}

@OptIn(ExperimentalPermissionsApi::class, ExperimentalMaterial3Api::class)
@Composable
fun Body(modifier: Modifier, navController: NavHostController) {
    val context = LocalContext.current
    val cameraController = remember {
        LifecycleCameraController(context)
    }

    val permissionState = rememberPermissionState(permission = Manifest.permission.CAMERA)
    LaunchedEffect(Unit) {
        permissionState.launchPermissionRequest()
    }

    if (permissionState.status.isGranted) {
        val lifeCycle = LocalLifecycleOwner.current

        Scaffold(
            bottomBar = {
                BottomAppBar(
                    //cutoutShape = ButtonDefaults.outlinedCutoutShape,
                    contentPadding = PaddingValues(8.dp)
                ) {
                    OutlinedButton(
                        onClick = {
                            val executor = ContextCompat.getMainExecutor(context)
                            navController.navigate(route ="gallery_screen")
                        },
                        modifier = Modifier.weight(1f) // Ocupa el mismo espacio en la fila
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally, // Centra horizontalmente
                            verticalArrangement = Arrangement.Center, // Centra verticalmente
                        ) {
                            Icon(
                                imageVector = Icons.Default.ViewList, // Reemplaza con el ícono que desees
                                contentDescription = "Listado Rx",
                                tint = Color.White // Opcional: ajusta el color del ícono
                            )
                            //Text(text = "Listado Rx") // Agrega un texto debajo del ícono
                        }
                    }

                    Spacer(modifier = Modifier.weight(0.1f)) // Espacio en blanco a la derecha

                    OutlinedButton(
                        onClick = {
                            val executor = ContextCompat.getMainExecutor(context)
                            // Agrega aquí la lógica de captura de imagen
                        },
                        modifier = Modifier
                            .weight(1f)
                            .then(
                                Modifier.background(Color(0xFFCC1B2B), shape = RoundedCornerShape(percent = 50))
                            )
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally, // Centra horizontalmente
                            verticalArrangement = Arrangement.Center, // Centra verticalmente
                        ) {
                            Icon(
                                imageVector = Icons.Default.Camera, // Reemplaza con el ícono que desees
                                contentDescription = "Capture",
                                tint = Color.White // Opcional: ajusta el color del ícono
                            )
                            // Text(text = "Capture") // Agrega un texto debajo del ícono
                        }
                    }



                    Spacer(modifier = Modifier.weight(0.1f)) // Espacio en blanco a la derecha

                    OutlinedButton(
                        onClick = {
                            val executor = ContextCompat.getMainExecutor(context)

                            // Define una transición personalizada

                            navController.navigate(route ="menu_screen"){
                                //popUpTo("camera_screen") { inclusive = true }
                                launchSingleTop = true // Opcional, evita que se cree una nueva instancia de la pantalla si ya está en la parte superior.
                            }
                        },
                        modifier = Modifier.weight(1f) // Ocupa el mismo espacio en la fila
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally, // Centra horizontalmente
                            verticalArrangement = Arrangement.Center, // Centra verticalmente
                        ) {
                            Icon(
                                imageVector = Icons.Default.AccountCircle, // Reemplaza con el ícono que desees
                                contentDescription = "account",
                                tint = Color.White // Opcional: ajusta el color del ícono
                            )
                            //Text(text = "Afiliado") // Agrega un texto debajo del ícono
                        }
                    }
                }

            }

        ) {
            if (permissionState.status.isGranted) {
                Camera(cameraController, lifeCycle, Modifier.padding(it))
            } else {
                Text(text = "Camera Off", modifier = Modifier.padding(it))
            }
        }
    }
}

@Composable
fun Camera(
    cameraController: LifecycleCameraController,
    lifeCycle: LifecycleOwner,
    modifier: Modifier = Modifier
) {
    cameraController.bindToLifecycle(lifeCycle)

    AndroidView(modifier = Modifier, factory = { context ->
        val previewView = PreviewView(context).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }
        previewView.controller = cameraController
        previewView
    })
}
@Composable
fun FloatingActionButtons(viewModel: CameraViewModel) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        contentAlignment = Alignment.TopEnd
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            FloatingActionButton(
                onClick = {
                    viewModel.showCustomDialog()
                },
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Botón 1")
            }

        }
    }
}



@Composable
fun CustomDialog(
    showDialog: Boolean,
    onClose: () -> Unit
) {
    if (showDialog) {
        Dialog(
            onDismissRequest = onClose
        ) {
            // Column que contiene el contenido del diálogo
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                // Tarjetas principales (Rx del mes y Rx del día)

                    Card(
                        modifier = Modifier
                            .weight(1f)
                            .padding(8.dp)
                    ) {
                        // Contenido de la tarjeta "Rx del mes" (información ficticia)
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            // Row para alinear el icono y el texto en la esquina superior derecha
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                // Icono en la esquina superior derecha
                                Icon(
                                    imageVector = Icons.Default.CalendarToday,
                                    contentDescription = null,
                                )

                                // Texto en la esquina superior izquierda
                                Text(
                                    text = "Información",
                                    fontSize = 12.sp,
                                    modifier = Modifier.align(Alignment.CenterVertically)
                                )
                            }

                            // Número centrado con fuente grande y negrita
                            Text(
                                text = "42",
                                fontSize = 32.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp)
                            )

                            // Texto "Rx del mes"
                            Text(
                                text = "Rx del Día, Hoy",
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp)
                            )
                        }
                    }

                    Card(
                        modifier = Modifier
                            .weight(1f)
                            .padding(8.dp)
                    ) {
                        // Contenido de la tarjeta "Rx del Semana" (información ficticia)
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            // Row para alinear el icono y el texto en la esquina superior derecha
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                // Icono en la esquina superior derecha
                                Icon(
                                    imageVector = Icons.Default.CalendarViewWeek,
                                    contentDescription = null,
                                )

                                // Texto en la esquina superior izquierda
                                Text(
                                    text = "Información",
                                    fontSize = 12.sp,
                                    modifier = Modifier.align(Alignment.CenterVertically)
                                )
                            }

                            // Número centrado con fuente grande y negrita
                            Text(
                                text = "42",
                                fontSize = 32.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp)
                            )

                            // Texto "Rx del mes"
                            Text(
                                text = "Rx de la semana",
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp)
                            )
                        }
                    }



                // Tarjeta inferior (Rx de mes)
                Card(
                    modifier = Modifier
                        //.weight(1f)
                        .padding(8.dp)
                ) {
                    // Contenido de la tarjeta "Rx del mes" (información ficticia)
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        // Row para alinear el icono y el texto en la esquina superior derecha
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            // Icono en la esquina superior derecha
                            Icon(
                                imageVector = Icons.Default.CalendarMonth,
                                contentDescription = null,
                            )

                            // Texto en la esquina superior izquierda
                            Text(
                                text = "Información",
                                fontSize = 12.sp,
                                modifier = Modifier.align(Alignment.CenterVertically)
                            )
                        }

                        // Número centrado con fuente grande y negrita
                        Text(
                            text = "42",
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                        )

                        // Texto "Rx del mes"
                        Text(
                            text = "Rx del mes",
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                        )
                    }
                }


                Spacer(modifier = Modifier.height(16.dp))

                // Row para el texto del Switch y el Switch
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Activar carga automática",
                        modifier = Modifier
                            .weight(1f)
                    )

                    // Switch (simplemente para la demostración)
                    Switch(
                        checked = false,
                        onCheckedChange = { /* Actualiza el valor según tu lógica */ },
                        modifier = Modifier
                    )
                }

                Spacer(modifier = Modifier.height(30.dp))

                // Botón "Cerrar" centrado en la parte inferior
                Button(
                    onClick = onClose,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                        .align(Alignment.CenterHorizontally)
                ) {
                    Text(text = "Cerrar")
                }
            }
        }
    }
}





