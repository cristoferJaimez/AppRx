package com.dev.apprx.register.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.dev.apprx.R
import com.dev.apprx.login.ui.Header
import com.dev.apprx.register.ui.viewModel.keywordViewModel

@Composable
fun KeywordScreen(keywordViewModel: keywordViewModel){

    Box(
        Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {

        Header(Modifier.align(Alignment.TopEnd))
        Body(Modifier.align(Alignment.Center), keywordViewModel)

    }
}

@Composable
private fun Body(modifier: Modifier, keywordViewModel: keywordViewModel) {
    Box(modifier = modifier ){
        val key: String by keywordViewModel.keyword.observeAsState(initial = "")
        val iskeywordenable : Boolean by keywordViewModel.isKeywordEnable.observeAsState(initial = false)

        keyword(key, iskeywordenable){
            keywordViewModel.onKeyChanged(key = it)
        }
        Spacer(modifier = Modifier.size(16.dp))

    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun keyword(key: String, isKeywordEnabled: Boolean, onTextChanged: (String) -> Unit) {
    val iconColor = if (isKeywordEnabled) {
        Color(0xFF4CAF50)  // Color para habilitado
    } else {
        Color.Gray  // Color para deshabilitado
    }

    TextField(
        value = key,
        label = { Text(text = "¿Quién eres?") },
        onValueChange = { onTextChanged(it) },
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = "Palabra clave asignada.") },
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        colors = TextFieldDefaults.run {
            textFieldColors(
                containerColor = Color(0xFFE7F0F7),
                textColor = Color(0xFF992E2E),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            )
        },
        trailingIcon = {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Default.VerifiedUser,
                    contentDescription = "icon usuario",
                    tint = iconColor  // Cambia el color del icono según isKeywordEnabled
                )
            }
        }
    )
}

