package com.dev.apprx.register.ui

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ImageNotSupported
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dev.apprx.R
import com.dev.apprx.login.ui.Header

@Composable
fun GeoLocationScreen() {
    Box(
        Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Header(Modifier.align(Alignment.TopEnd))
        Body(Modifier.align(Alignment.Center))
    }
}


@Composable
private fun Body(modifier: Modifier) {
    Column(modifier = modifier) {
        Text(text = "Geo Locations")
        Spacer(modifier = Modifier.height(16.dp)) // Añade espacio antes de las imágenes
        ImagesRow() // Agrega las imágenes aquí
        Spacer(modifier = Modifier.height(8.dp)) // Puedes agregar espacio entre elementos si lo necesitas
        TextFieldCountry()
        Spacer(modifier = Modifier.height(8.dp))
        TextFieldCity()
        Spacer(modifier = Modifier.height(8.dp))
        TextFieldAdress()
        Spacer(modifier = Modifier.height(8.dp))
        TextFieldLat()
        Spacer(modifier = Modifier.height(8.dp))
        TextFieldLng()

    }
}


@Composable
fun ImagesRow() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween // Esto es para asegurar que las imágenes se distribuyan en ambos extremos
    ) {
        ImageComponent(imageResource = R.drawable.ic_launcher)
        ImageComponent(imageResource = R.drawable.ic_launcher)
    }
}

@Composable
fun ImageComponent(imageResource: Int) {
    val context = LocalContext.current
    Image(
        painter = painterResource(id = imageResource),
        contentDescription = null, // Añadir una descripción adecuada para la accesibilidad
        modifier = Modifier
            .width(200.dp)
            .fillMaxWidth()
            .size(100.dp) // Tamaño de la imagen. Puedes ajustarlo como desees.
            .clickable {
                // Aquí va el código para maximizar o mostrar la imagen en un modal
                Toast.makeText(context, "Imagen presionada", Toast.LENGTH_SHORT).show()
            }
    )
}


@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldLng() {
    TextField(
        value = "",
        label = { Text(text = "Longitud") },
        onValueChange = {  },
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = "Longitud") },
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        colors = TextFieldDefaults.run {
            textFieldColors(
                containerColor = Color(0xFFE7F0F7),
                textColor = Color(0xFF992E2E),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            )
        },
        leadingIcon = {
            val painter = painterResource(id = R.drawable.ic_launcher)
            Image(
                painter = painter,
                contentDescription = "Descripción de la imagen",
                modifier = Modifier
                    .size(40.dp) // puedes ajustar el tamaño
                    .clip(CircleShape)
                    .clickable { /* acción al hacer clic */ }
            )
        }

    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldLat() {
    TextField(
        value = "",
        label = { Text(text = "Latitud") },
        onValueChange = {  },
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = "Latitud") },
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        colors = TextFieldDefaults.run {
            textFieldColors(
                containerColor = Color(0xFFE7F0F7),
                textColor = Color(0xFF992E2E),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            )
        },
        leadingIcon = {
            val painter = painterResource(id = R.drawable.ic_launcher)
            Image(
                painter = painter,
                contentDescription = "Descripción de la imagen",
                modifier = Modifier
                    .size(40.dp) // puedes ajustar el tamaño
                    .clip(CircleShape)
                    .clickable { /* acción al hacer clic */ }
            )
        }

    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldAdress() {
    TextField(
        value = "",
        label = { Text(text = "Dirección") },
        onValueChange = {  },
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = "Dirección") },
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        colors = TextFieldDefaults.run {
            textFieldColors(
                containerColor = Color(0xFFE7F0F7),
                textColor = Color(0xFF992E2E),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            )
        },
        leadingIcon = {
            val painter = painterResource(id = R.drawable.ic_launcher)
            Image(
                painter = painter,
                contentDescription = "Descripción de la imagen",
                modifier = Modifier
                    .size(40.dp) // puedes ajustar el tamaño
                    .clip(CircleShape)
                    .clickable { /* acción al hacer clic */ }
            )
        }

    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldCity() {
    TextField(
        value = "",
        label = { Text(text = "Ciudad") },
        onValueChange = {  },
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = "Ciudad") },
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        colors = TextFieldDefaults.run {
            textFieldColors(
                containerColor = Color(0xFFE7F0F7),
                textColor = Color(0xFF992E2E),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            )
        },
        leadingIcon = {
            val painter = painterResource(id = R.drawable.ic_launcher)
            Image(
                painter = painter,
                contentDescription = "Descripción de la imagen",
                modifier = Modifier
                    .size(40.dp) // puedes ajustar el tamaño
                    .clip(CircleShape)
                    .clickable { /* acción al hacer clic */ }
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun  TextFieldCountry(){

    TextField(
        value = "",
        label = { Text(text = "Pais") },
        onValueChange = {  },
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = "Pais") },
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        colors = TextFieldDefaults.run {
            textFieldColors(
                containerColor = Color(0xFFE7F0F7),
                textColor = Color(0xFF992E2E),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            )
        },
        leadingIcon = {
            val painter = painterResource(id = R.drawable.ic_launcher)
            Image(
                painter = painter,
                contentDescription = "Descripción de la imagen",
                modifier = Modifier
                    .size(40.dp) // puedes ajustar el tamaño
                    .clip(CircleShape)
                    .clickable { /* acción al hacer clic */ }
            )
        }
    )
}

