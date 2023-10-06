package com.dev.apprx.register.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dev.apprx.login.ui.Header

@Composable
fun VerificationScreen() {
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
    Box(modifier = modifier ){
        Text(text = "Verification Screen")
        PreviewUserDataCard()
    }
}



@Composable
fun PreviewUserDataCard() {
    val sampleData = arrayListOf(
        "Nombre de Farmacia: Juan",
        "Cadena: juan@email.com",
        "Adrress: 123-456-7890",
        "Dirección: Calle Falsa 123"
    )

    UserDataCard(data = sampleData)
}

@Composable
fun UserDataCard(data: ArrayList<String>) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(16.dp),  // Bordes redondeados
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            data.forEach { item ->
                Text(text = item)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

