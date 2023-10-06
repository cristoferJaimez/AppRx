package com.dev.apprx.register.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dev.apprx.login.ui.Header


@Composable
fun WelcomeScreen() {
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
            Text(text = "Welcome to Register")
        }
}
