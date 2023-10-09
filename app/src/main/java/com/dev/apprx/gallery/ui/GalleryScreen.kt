package com.dev.apprx.gallery.ui

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.CheckCircleOutline
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.ImageNotSupported
import androidx.compose.material.icons.filled.ListAlt
import androidx.compose.material.icons.filled.Upload
import androidx.compose.material.icons.filled.Usb
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dev.apprx.R
import java.lang.Math.ceil
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@Composable
fun GalleryScreen(navController: NavHostController, context: Context) {
    val permission = Manifest.permission.READ_EXTERNAL_STORAGE
    val granted = PackageManager.PERMISSION_GRANTED

    if (ContextCompat.checkSelfPermission(context, permission) != granted) {
        // Solicitar permiso
        ActivityCompat.requestPermissions(context as Activity, arrayOf(permission), 100)
    }

    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column {
            Heard(navController)
            TabLayout(context)
            Spacer(modifier = Modifier.width(30.dp))
        }
    }
}


@Composable
fun Heard(navController: NavHostController) {
    // Encabezado con botón de retroceso
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFCC1B2B))
            .padding(bottom = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically  // Centrar verticalmente el contenido
    ) {
        IconButton(onClick = { }) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Atrás",
                tint = Color.White,  // Pintar el ícono de blanco
                modifier = Modifier.clickable {
                    navController.popBackStack()
                    Log.i("row", "touch row")
                }
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp) // Espacio entre los íconos y el texto
        ) {
            Text(
                text = "Rx Disponibles".toUpperCase(),
                style = MaterialTheme.typography.bodySmall,
                color = Color.White,  // Cambiar el color del texto a blanco
                modifier = Modifier
                    .padding(end = 16.dp)  // Agregar un margen/padding al texto
                    .wrapContentWidth(Alignment.End)
            )

            //Icon(imageVector = Icons.Default.VerifiedUser, contentDescription = "Icono 1", tint = Color.White)
        }


    }
}


@Composable
fun TabLayout(context: Context) {
    val (selectedTabIndex, setSelectedTabIndex) = remember { mutableStateOf(0) }



    Box(modifier = Modifier.background(Color(0xFFCC1B2B))) {
        TabRow(
            selectedTabIndex = selectedTabIndex,
            contentColor = Color.White, // Estableciendo todos los contenidos en blanco
            indicator = {} // Removiendo el indicador predeterminado
        ) {
            TabItem(
                icon = Icons.Default.Image,
                label = "Rx",
                isSelected = selectedTabIndex == 0,
                onClick = { setSelectedTabIndex(0) }
            )

            TabItem(
                icon = Icons.Default.BarChart,
                label = "Estadistica",
                isSelected = selectedTabIndex == 1,
                onClick = { setSelectedTabIndex(1) }
            )
            TabItem(
                icon = Icons.Default.ListAlt,
                label = "Planilla",
                isSelected = selectedTabIndex == 2,
                onClick = { setSelectedTabIndex(2) }
            )

        }
    }

    when (selectedTabIndex) {
        0 -> {
            Body(context)
        }

        1 -> {
            // Contenido de la segunda pestaña
        }

        2 -> {
            // Contenido de la tercera pestaña
        }
    }
}

@Composable
fun TabItem(
    icon: ImageVector,
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .background(if (isSelected) Color(0xFFD6414E) else Color(0xFFCC1B2B))
            .clickable(onClick = onClick)
            .padding(12.dp),
        contentAlignment = Alignment.Center // Centrando contenido en el Box
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                icon,
                contentDescription = label,
                tint = Color.White // Estableciendo el ícono en blanco
            )
            Text(
                label.toUpperCase(),
                color = Color.White,
                style = MaterialTheme.typography.bodySmall,
            ) // Estableciendo el texto en blanco
        }
    }
}



@Composable
fun Body(context: Context) {
    // Crear una instancia de GalleryViewModel
    val galleryViewModel = GalleryViewModel()
    val navController = rememberNavController()
    // Llamar a ImageBody con las instancias de GalleryViewModel y ContentResolver
    ImageBody(galleryViewModel, navController, context)
}

@Composable
fun ImageBody(viewModel: GalleryViewModel, navController: NavHostController, context: Context) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Paginación
        var currentPage by remember { mutableStateOf(1) }
        val itemsPerPage = 10

        // Llama a la función para cargar las imágenes y espera a que se complete
        LaunchedEffect(viewModel) {
            viewModel.loadGalleryImages(context.contentResolver)
        }

        // Observa el LiveData del ViewModel después de que se complete la carga
        val galleryImages by viewModel.getGalleryImages().observeAsState()

        // Mostrar las 10 imágenes por página
        val startIndex = (currentPage - 1) * itemsPerPage
        val endIndex = minOf(startIndex + itemsPerPage, galleryImages?.size ?: 0)
        val visibleImages = galleryImages?.subList(startIndex, endIndex)

        // Si no hay imágenes, muestra un mensaje
        if (galleryImages.isNullOrEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Default.DeleteOutline,
                        contentDescription = null,
                        modifier = Modifier.size(100.dp),
                        tint = Color.Gray
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "No hay imágenes",
                        color = Color.Gray
                    )
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
                    .fillMaxWidth(),
                content = {
                    items(visibleImages ?: emptyList()) { imageData ->
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                                .fillMaxHeight(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                // Imagen a la izquierda
                                val defaultImage = painterResource(id = R.drawable.ai_close_up_rx_100x100)
                                Image(
                                    painter = defaultImage,
                                    contentDescription = null,
                                    modifier = Modifier.size(50.dp)
                                )

                                Spacer(modifier = Modifier.width(8.dp)) // Separación entre imagen y texto

                                // Datos centrados: Nombre y fecha
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(start = 4.dp),
                                    verticalArrangement = Arrangement.spacedBy(2.dp), // Alineados verticalmente
                                    horizontalAlignment = Alignment.Start
                                ) {
                                    Text(
                                        text = imageData.name,
                                        fontSize = 12.sp // Reducir el tamaño de fuente
                                    )

                                    // Fila para la fecha y el ícono de verificación
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                                    ) {
                                        Text(
                                            text = "Fecha: ${viewModel.formatDate(imageData.dateAdded)}",
                                            color = Color.Gray,
                                            fontSize = 8.sp
                                        )
                                        Spacer(modifier = Modifier.width(4.dp)) // Separación entre fecha y check
                                        Icon(
                                            imageVector = Icons.Default.Upload,
                                            contentDescription = null,
                                            tint = Color.Green,
                                            modifier = Modifier.size(10.dp)
                                        )
                                        Text(
                                            text = "save",
                                            fontSize = 8.sp
                                        )
                                        Icon(
                                            imageVector = Icons.Default.Usb,
                                            contentDescription = null,
                                            tint = Color.Green,
                                            modifier = Modifier.size(10.dp)
                                        )
                                        Text(
                                            text = "save",
                                            fontSize = 8.sp
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            )



            // Paginación
            val totalPages = if (galleryImages != null) {
                // Calcular el número total de páginas asegurándote de incluir la última página si es necesario
                val totalImages = galleryImages!!.size
                (totalImages + itemsPerPage - 1) / itemsPerPage
            } else {
                1 // Por lo menos una página
            }

            Pagination(
                currentPage = currentPage,
                totalPages = totalPages,
                onNextPage = {
                    if (currentPage < totalPages) {
                        currentPage++
                    }
                },
                onPrevPage = {
                    if (currentPage > 1) {
                        currentPage--
                    }
                }
            )

        }

        // Card con botones
        Spacer(modifier = Modifier.width(16.dp))
        BottomCard()
    }
}



@Composable
fun Pagination(
    currentPage: Int, totalPages: Int, onNextPage: () -> Unit, onPrevPage: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Botón para la página anterior
        IconButton(
            onClick = { onPrevPage() }, enabled = currentPage > 1
        ) {
            Icon(imageVector = Icons.Default.ChevronLeft, contentDescription = "Página anterior")
        }

        Spacer(modifier = Modifier.width(16.dp))

        // Mostrar la página actual y el total de páginas
        Text(text = "Página $currentPage de $totalPages")

        Spacer(modifier = Modifier.width(16.dp))

        // Botón para la página siguiente
        IconButton(
            onClick = { onNextPage() }, enabled = currentPage < totalPages
        ) {
            Icon(imageVector = Icons.Default.ChevronRight, contentDescription = "Página siguiente")
        }
    }
}

@Composable
fun BottomCard() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp)
    ) {

        // Botones
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Botón USB
            Column(
                horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.weight(1f)
            ) {
                RoundedIconButton(
                    onClick = {
                        // Acción al hacer clic en el botón USB
                    }, icon = Icons.Default.Usb
                )
                Text(
                    "Pasar a USB".toUpperCase(),
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }
            /*
           // Botón Home
           Column(
               horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.weight(1f)
           ) {
               RoundedIconButton(
                   onClick = {
                       // Acción al hacer clic en el botón Home
                   }, icon = Icons.Default.BarChart
               )
               Text(
                   "Ciclo".toUpperCase(), style = MaterialTheme.typography.bodySmall, color = Color.Gray
               )
           }
           */
            // Botón Upload
            Column(
                horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.weight(1f)
            ) {
                RoundedIconButton(
                    onClick = {
                        // Acción al hacer clic en el botón Upload
                    }, icon = Icons.Default.Upload
                )
                Text(
                    text = "Subir a FTP".toUpperCase(),
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }
        }
    }
}


@Composable
fun RoundedIconButton(
    onClick: () -> Unit, icon: ImageVector
) {
    Box(
        modifier = Modifier
            .size(40.dp)
            .background(
                color = Color.Gray, shape = CircleShape
            )
            .border(
                width = 4.dp, color = Color.Gray, shape = CircleShape
            )
            .clickable(onClick = onClick), contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color.White,  // Para asegurar que el icono sea blanco
            modifier = Modifier.size(24.dp) // Ajusta el tamaño del icono
        )
    }
}





