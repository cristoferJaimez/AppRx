package com.dev.apprx.register.ui


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.dev.apprx.login.ui.Header


@Composable
fun PharmaScreen() {
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
    Box(modifier = modifier) {
        Column(modifier = modifier) {
            TextTitle(Modifier)
            Spacer(modifier = Modifier.height(16.dp)) // Añade espacio antes de las imágenes
            SelectTextFieldCountry()
        }
    }
}



@Preview
@Composable
fun SelectTextFieldCountry() {
    dropDownMenu()
    dropDownMenuCadena()
    dropDownMenuFarma()
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun dropDownMenuFarma() {
    var expanded by remember {
        mutableStateOf(false)
    }

    var list = listOf("CADENA", "INDEPENDIENTE", "COOPIDROGUISTA")

    var selectItem by remember {
        mutableStateOf("")
    }

    var textFieldSize by remember {
        mutableStateOf(Size.Zero)
    }

    val icon = if (expanded) {
        Icons.Filled.KeyboardArrowUp
    } else {
        Icons.Filled.KeyboardArrowDown
    }

    Column(modifier = Modifier.padding(8.dp)) {
        OutlinedTextField(
            value = selectItem,
            onValueChange = { selectItem = it },
            label = { Text(text = "Seleccione farmacia") },
            colors = TextFieldDefaults.run {
                textFieldColors(
                    containerColor = Color(0xFFE7F0F7),
                    textColor = Color(0xFF992E2E),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                )
            },
            trailingIcon = {
                Icon(imageVector = icon, contentDescription = null, modifier = Modifier.clickable {
                    expanded = !expanded
                })
            },
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    textFieldSize = coordinates.size.toSize()
                },
            readOnly = true
        )


        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.width(with(LocalDensity.current) { textFieldSize.width.toDp() })
        ) {
            list.forEach { label ->
                DropdownMenuItem(
                    modifier = Modifier.background(Color(0xFF0000)),
                    text = { label },
                    onClick = {
                        selectItem = label
                        expanded = false
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun dropDownMenuCadena() {
    var expanded by remember {
        mutableStateOf(false)
    }

    var list = listOf("CADENA", "INDEPENDIENTE", "COOPIDROGUISTA")

    var selectItem by remember {
        mutableStateOf("")
    }

    var textFieldSize by remember {
        mutableStateOf(Size.Zero)
    }

    val icon = if (expanded) {
        Icons.Filled.KeyboardArrowUp
    } else {
        Icons.Filled.KeyboardArrowDown
    }

    Column(modifier = Modifier.padding(8.dp)) {
        OutlinedTextField(
            value = selectItem,
            onValueChange = { selectItem = it },
            label = { Text(text = "Seleccione cadena") },
            colors = TextFieldDefaults.run {
                textFieldColors(
                    containerColor = Color(0xFFE7F0F7),
                    textColor = Color(0xFF992E2E),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                )
            },
            trailingIcon = {
                Icon(imageVector = icon, contentDescription = null, modifier = Modifier.clickable {
                    expanded = !expanded
                })
            },
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    textFieldSize = coordinates.size.toSize()
                },
            readOnly = true
        )


        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.width(with(LocalDensity.current) { textFieldSize.width.toDp() })
        ) {
            list.forEach { label ->
                DropdownMenuItem(
                    modifier = Modifier.background(Color(0xFF0000)),
                    text = { label },
                    onClick = {
                        selectItem = label
                        expanded = false
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun dropDownMenu() {
    var expanded by remember {
        mutableStateOf(false)
    }

    var list = listOf("CADENA", "INDEPENDIENTE", "COOPIDROGUISTA")

    var selectItem by remember {
        mutableStateOf("")
    }

    var textFieldSize by remember {
        mutableStateOf(Size.Zero)
    }

    val icon = if (expanded) {
        Icons.Filled.KeyboardArrowUp
    } else {
        Icons.Filled.KeyboardArrowDown
    }

    Column(modifier = Modifier.padding(8.dp)) {
        OutlinedTextField(
            value = selectItem,
            onValueChange = { selectItem = it },
            label = { Text(text = "Seleccione tipo") },
            colors = TextFieldDefaults.run {
                textFieldColors(
                    containerColor = Color(0xFFE7F0F7),
                    textColor = Color(0xFF992E2E),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                )
            },
            trailingIcon = {
                Icon(imageVector = icon, contentDescription = null, modifier = Modifier.clickable {
                    expanded = !expanded
                })
            },
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    textFieldSize = coordinates.size.toSize()
                },
            readOnly = true
        )


        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.width(with(LocalDensity.current) { textFieldSize.width.toDp() })
        ) {
            list.forEach { label ->
                DropdownMenuItem(
                    modifier = Modifier.background(Color(0xFF0000)),
                    text = { label },
                    onClick = {
                        selectItem = label
                        expanded = false
                    }
                )
            }
        }
    }
}


@Composable
fun TextTitle(modifier: Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally, // Centra los elementos horizontalmente dentro de la Column
    ) {
        Text(text = "Farmacias", textAlign = TextAlign.Center) // Alineación del texto al centro
    }
}




