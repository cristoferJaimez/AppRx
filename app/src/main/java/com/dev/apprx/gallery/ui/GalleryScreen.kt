package com.dev.apprx.gallery.ui

import androidx.navigation.NavHostController
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
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
import androidx.compose.material.icons.filled.ImageNotSupported
import androidx.compose.material.icons.filled.Upload
import androidx.compose.material.icons.filled.Usb
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.dev.apprx.R
import com.dev.apprx.rout.Navigation
import com.dev.apprx.rout.Screen



@Composable
fun GalleryScreen() {


    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Body()
    }
}


@Composable
fun Body() {
    // Crear una instancia de GalleryViewModel
    val galleryViewModel = GalleryViewModel()
    val navController = rememberNavController()
    // Llamar a ImageBody con las instancias de GalleryViewModel y ContentResolver
    ImageBody(galleryViewModel, navController)
}


@Composable
fun ImageBody(viewModel: GalleryViewModel, navController: NavHostController) {

    val galleryImages by viewModel.getGalleryImages().observeAsState()
        // Llama a la función para cargar las imágenes



    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),

        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Paginación
        var currentPage by remember { mutableStateOf(1) }
        val totalPages = 10

        // Encabezado con botón de retroceso
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween, // Cambiado a SpaceBetween
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = {


                }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Atrás",
                    Modifier.clickable {
                        Log.i("row", "touch row")
                        navController.popBackStack()
                    })
            }
            Spacer(modifier = Modifier.width(16.dp))

            // Texto "GALERÍA DE IMÁGENES" con mayor peso
            Text(
                text = "Rx Disponibles".toUpperCase(),
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray,
                modifier = Modifier
                    .weight(1f) // Peso mayor
                    .wrapContentWidth(Alignment.End) // Alineación a la derecha
            )

            // Agregamos cualquier otro elemento que desees a la derecha

        }


        // Llama a la función para cargar las imágenes
        LaunchedEffect(Unit) {
            //viewModel.loadGalleryImages()
        }

        // Mostrar las 10 imágenes
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            content = {
                if (galleryImages != null && galleryImages!!.isNotEmpty()) {
                    items(galleryImages!!) { imageData ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            // Resto del código para mostrar cada imagen en la lista
                            // Icono de imagen
                            Image(
                                painter = painterResource(id = R.drawable.ic_launcher),
                                contentDescription = imageData.toString(),
                                modifier = Modifier
                                    .size(30.dp)
                                    .clip(MaterialTheme.shapes.small)
                                    .background(Color.Gray),
                                contentScale = ContentScale.Crop
                            )

                            // Datos centrales: Nombre y fecha
                            Column(
                                modifier = Modifier
                                    .weight(1f) // Hace que esta columna tome el espacio disponible
                                    .padding(start = 16.dp) // Agregamos un padding para separar del ícono de la imagen
                                    .fillMaxHeight(), // La columna ocupará toda la altura disponible
                                verticalArrangement = Arrangement.Center, // Centramos verticalmente
                                horizontalAlignment = Alignment.CenterHorizontally // Centramos horizontalmente
                            ) {
                                Text(
                                    text = imageData.toString().toUpperCase(),
                                    modifier = Modifier.padding(bottom = 2.dp)
                                )
                                Text(
                                    text = "Fecha de captura",
                                    color = Color.Gray
                                )
                            }

                            // Iconos de check
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(4.dp), // Ajusta el espacio entre los íconos
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Default.CheckCircleOutline,
                                    contentDescription = "Check 1",
                                    tint = Color.Gray,
                                    modifier = Modifier
                                        .size(24.dp)
                                )
                                Icon(
                                    imageVector = Icons.Default.CheckCircleOutline,
                                    contentDescription = "Check 2",
                                    tint = Color.Gray,
                                    modifier = Modifier
                                        .size(24.dp)
                                )
                            }
                        }
                    }
                } else {
                    item {
                        Column(
                            modifier = Modifier
                                .fillMaxSize() // Ocupa todo el espacio disponible
                                .padding(16.dp)
                                .fillMaxHeight()
                                .wrapContentSize(Alignment.Center)
                        ) {
                            Icon(
                                imageVector = Icons.Default.ImageNotSupported,
                                contentDescription = null,
                                tint = Color.Gray,
                                modifier = Modifier
                                    .size(48.dp)
                                    .padding(bottom = 8.dp)
                            )
                            Text(
                                text = "Sin imágenes",
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.Gray,
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            )
                        }


                    }
                }
            }
        )


        // Paginación
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
        // Card con botones
        Spacer(modifier = Modifier.width(16.dp))
        BottomCard()
    }

}

@Composable
fun Pagination(
    currentPage: Int,
    totalPages: Int,
    onNextPage: () -> Unit,
    onPrevPage: () -> Unit
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
            onClick = { onPrevPage() },
            enabled = currentPage > 1
        ) {
            Icon(imageVector = Icons.Default.ChevronLeft, contentDescription = "Página anterior")
        }

        Spacer(modifier = Modifier.width(16.dp))

        // Mostrar la página actual y el total de páginas
        Text(text = "Página $currentPage de $totalPages")

        Spacer(modifier = Modifier.width(16.dp))

        // Botón para la página siguiente
        IconButton(
            onClick = { onNextPage() },
            enabled = currentPage < totalPages
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
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(1f)
            ) {
                RoundedIconButton(
                    onClick = {
                        // Acción al hacer clic en el botón USB
                    },
                    icon = Icons.Default.Usb
                )
                Text(
                    "Pasar a USB",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }

            // Botón Home
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(1f)
            ) {
                RoundedIconButton(
                    onClick = {
                        // Acción al hacer clic en el botón Home
                    },
                    icon = Icons.Default.BarChart
                )
                Text(
                    "Ciclo",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }

            // Botón Upload
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(1f)
            ) {
                RoundedIconButton(
                    onClick = {
                        // Acción al hacer clic en el botón Upload
                    },
                    icon = Icons.Default.Upload
                )
                Text(
                    text = "Subir a FTP",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }
        }
    }
}


@Composable
fun RoundedIconButton(
    onClick: () -> Unit,
    icon: ImageVector
) {
    Box(
        modifier = Modifier
            .size(40.dp)
            .background(
                color = Color.Gray,
                shape = CircleShape
            )
            .border(
                width = 4.dp,
                color = Color.Gray,
                shape = CircleShape
            )
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color.White,  // Para asegurar que el icono sea blanco
            modifier = Modifier.size(24.dp) // Ajusta el tamaño del icono
        )
    }
}





