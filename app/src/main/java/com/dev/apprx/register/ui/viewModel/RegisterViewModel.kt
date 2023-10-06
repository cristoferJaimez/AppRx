package com.dev.apprx.register.ui.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class RegisterViewModel : ViewModel() {

    // Estado para almacenar la sección actual
    private val _currentSection = MutableStateFlow(0)
    val currentSectionFlow: Flow<Int> get() = _currentSection.asStateFlow()

    // Función para ir a la sección anterior
    fun navigateToPreviousSection() {
        if (_currentSection.value > 0) {
            _currentSection.value -= 1
            Log.i("btn", "PREV ${_currentSection.value}")
        }
    }

    // Función para el btn de ayuda
    fun help(){
        Log.i("btn", "HELP")
    }

    // Función para ir a la siguiente sección
    fun navigateToNextSection(totalSections: Int) {
        if (_currentSection.value < totalSections - 1) {
            _currentSection.value += 1
            Log.i("btn", "NEXT ${_currentSection.value}")
        }
    }

    // Ver la vista actual
    fun view(view: Int){
        Log.i("view", "view $view")
    }
}
