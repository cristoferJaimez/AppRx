package com.dev.apprx.register.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import com.dev.apprx.register.ui.viewModel.keywordViewModel
import com.dev.apprx.register.ui.viewModel.RegisterViewModel

@Composable
fun RegisterScreen() {
    val viewModel = remember { RegisterViewModel() }
    val keyViewModel = remember { keywordViewModel() }

    val currentSection = viewModel.currentSectionFlow.collectAsState(initial = 0).value

    val totalSections = 5

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Espacio para la vista actual
        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.Center
        ) {
            when (currentSection) {
                0 -> WelcomeScreen()
                1 -> KeywordScreen(keyViewModel)
                2 -> GeoLocationScreen()
                3 -> PharmaScreen()
                4 -> VerificationScreen()
            }
        }

        // Botones de navegación
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    viewModel.navigateToPreviousSection()
                },
                enabled = currentSection > 0
            ) {
                Text("Anterior")
            }

            Button(
                onClick = {
                    viewModel.help()
                }
            ) {
                Text("Ayuda")
            }

            Button(
                onClick = {
                    viewModel.navigateToNextSection(totalSections)
                },
                enabled = currentSection < totalSections - 1
            ) {
                Text("Siguiente")
            }
        }
    }
}


