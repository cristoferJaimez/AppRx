package com.dev.apprx.menu.ui

import android.view.MenuItem
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dev.apprx.R

@Composable
fun MenuScreen() {
    Box(
        Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        PreviewMenuBody()
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMenuBody() {
    Column(modifier = Modifier) {
        Header()
        MenuBody()
    }

}
@Composable
fun Header() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Imagen centrada redondeada
        Image(
            painter = painterResource(id = R.drawable.ic_launcher),
            contentDescription = null,
            modifier = Modifier
                .size(80.dp) // Cambia el tamaño según tus necesidades
                .clip(CircleShape)
                .background(Color.Gray), // Añade un color de fondo
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Texto en negrita debajo de la imagen
        Text(
            text = "Tu Nombre",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )

        Spacer(modifier = Modifier.height(4.dp))

        // Texto más pequeño debajo del texto en negrita
        Text(
            text = "Información adicional",
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Línea gris fina
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(0.5.dp)
                .background(Color.Gray)
        )
    }
}
@Composable
fun MenuItem(icon: @Composable () -> Unit, text: String, textAlignToEnd: Boolean = false) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .selectable(selected = false, onClick = {}),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = if (textAlignToEnd) Arrangement.SpaceBetween else Arrangement.Start
    ) {
        Box(
            modifier = Modifier
                .size(24.dp)
                .padding(end = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            icon()
        }
        Text(text = text)
    }
}
@Composable
fun MenuBody() {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        MenuItem(icon = { Icon(imageVector = Icons.Default.ArrowForwardIos, contentDescription = null) }, text = "Carga de Rx")
        MenuItem(icon = { Icon(imageVector = Icons.Default.ArrowForwardIos, contentDescription = null) }, text = "Planilla Digital")
        Spacer(modifier = Modifier.weight(1f))
        MenuItem(icon = { Icon(imageVector = Icons.Default.ExitToApp, contentDescription = null) }, text = "Cerrar sesión")
    }
}


