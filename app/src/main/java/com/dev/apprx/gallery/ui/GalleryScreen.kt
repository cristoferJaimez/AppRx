package com.dev.apprx.gallery.ui

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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dev.apprx.R


@Composable
fun GalleryScreen() {
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column {
            Heard()
            TabLayout()
            Spacer(modifier = Modifier.width(30.dp))
        }
    }
}


@Composable
fun Heard() {
    // Encabezado con botón de retroceso
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF773434))
            .padding(bottom = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically  // Centrar verticalmente el contenido
    ) {
        IconButton(onClick = {}) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Atrás",
                tint = Color.White,  // Pintar el ícono de blanco
                modifier = Modifier.clickable {
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
fun TabLayout() {
    val (selectedTabIndex, setSelectedTabIndex) = remember { mutableStateOf(0) }

    Box(modifier = Modifier.background(Color(0xFF773434))) {
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
                label = "Planilla Digital",
                isSelected = selectedTabIndex == 3,
                onClick = { setSelectedTabIndex(3) }
            )

        }
    }

    when (selectedTabIndex) {
        0 -> {
            Body()
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
            .background(if (isSelected) Color(0xFF885555) else Color(0xFF773434))
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


@Preview(showBackground = true)
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

        verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Paginación
        var currentPage by remember { mutableStateOf(1) }
        val totalPages = 10


        // Llama a la función para cargar las imágenes
        LaunchedEffect(Unit) {
            //viewModel.loadGalleryImages()
        }

        // Mostrar las 10 imágenes
        LazyColumn(modifier = Modifier
            .weight(1f)
            .fillMaxSize()
            .fillMaxWidth(), content = {
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
                                text = "Fecha de captura", color = Color.Gray
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
                                modifier = Modifier.size(24.dp)
                            )
                            Icon(
                                imageVector = Icons.Default.CheckCircleOutline,
                                contentDescription = "Check 2",
                                tint = Color.Gray,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }
                }
            } else {
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.Center, // Centra verticalmente los hijos
                        horizontalAlignment = Alignment.CenterHorizontally // Centra horizontalmente los hijos
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
                            color = Color.Gray
                        )
                    }

                }
            }
        })


        // Paginación
        Pagination(currentPage = currentPage, totalPages = totalPages, onNextPage = {
            if (currentPage < totalPages) {
                currentPage++
            }
        }, onPrevPage = {
            if (currentPage > 1) {
                currentPage--
            }
        })
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





