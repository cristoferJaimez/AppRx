package com.dev.apprx.login.ui

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.dev.apprx.R
import com.dev.apprx.rout.navigate.AppScreens


@Composable
fun LoginScreen(loginViewModel: LoginViewModel, navController: NavController) {
    Box(
        Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        val isLoading: Boolean by loginViewModel.isLoading.observeAsState(initial = false)
        if (isLoading) {
            Box(
                modifier = Modifier
                          .fillMaxSize()
                           .align(Alignment.CenterEnd)
            ) {
                CircularProgressIndicator()
            }
        } else {
            Header(Modifier.align(Alignment.TopEnd))
            Body(Modifier.align(Alignment.Center), loginViewModel, navController)
            Footer(Modifier.align(Alignment.BottomCenter))
        }
    }
}

@Composable
fun Footer(modifier: Modifier) {
    Column(modifier = modifier.fillMaxWidth()) {
        Divider(
            Modifier
                .background(Color(0xFFF9F9F9))
                .height(1.dp)
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.size(24.dp))
        SignUp()
        Spacer(modifier = Modifier.size(24.dp))

    }
}

@Composable
fun SignUp() {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        Text(text = "Manual de la app", fontSize = 12.sp, color = Color(0xFFB5B5B5))
        Text(
            text = "Descargar",
            Modifier.padding(horizontal = 8.dp),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF4EA8E9)
        )
    }
}

@Composable
fun Body(modifier: Modifier, loginViewModel: LoginViewModel, navController: NavController) {


    val email: String by loginViewModel.email.observeAsState(initial = "")
    val password: String by loginViewModel.password.observeAsState(initial = "")
    val isLoginEnable: Boolean by loginViewModel.isLoginEnable.observeAsState(initial = false)

    Column(modifier = modifier) {
        ImageLogo(Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.size(8.dp))
        Email(email) {
            loginViewModel.onLoginChanged(email = it, password = password)
        }
        Spacer(modifier = Modifier.size(4.dp))
        Password(password) {
            loginViewModel.onLoginChanged(email = email, password = it)
        }
        Spacer(modifier = Modifier.size(8.dp))
        //ForgotPassword(Modifier.align(Alignment.End))
        Spacer(modifier = Modifier.size(16.dp))
        LoginButton(isLoginEnable, loginViewModel)
        Spacer(modifier = Modifier.size(2.dp))
        RegisterButton(loginViewModel, navController)
        Spacer(modifier = Modifier.size(16.dp))
        LoginDivider()
        Spacer(modifier = Modifier.size(32.dp))
        SocialLogin()
    }
}


@Composable
fun SocialLogin() {
    Row(
        Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher),
            contentDescription = "Social",
            Modifier.size(16.dp)
        )
        Text(
            text = "Continue a Close up",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 8.dp),
            color = Color(0xFF4EA8E9)
        )
    }
}


@Composable
fun LoginDivider() {
    Row(
        Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Divider(
            Modifier
                .background(Color(0xFFF9F9F9))
                .height(1.dp)
                .weight(1f)
        )
        Text(
            text = "Soluciones Inteligentes",
            modifier = Modifier.padding(horizontal = 18.dp),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFB5B5B5)
        )
        Divider(
            Modifier
                .background(Color(0xFFF9F9F9))
                .height(1.dp)
                .weight(1f)
        )
    }
}

@Composable
fun RegisterButton(loginViewModel: LoginViewModel, navController: NavController) {
    Button(
        onClick = { navController.navigate(route = "register_screen")},
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = "Registrar Farmacia")
    }
}


@Composable
fun LoginButton(loginEnabled: Boolean, loginViewModel: LoginViewModel) {
    Button(
        onClick = { loginViewModel.onLoginSelected() },
        enabled = loginEnabled,
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF4EA8E9),
            disabledContainerColor = Color(0xFF78C8F9),
            contentColor = Color.White,
            disabledContentColor = Color.White
        )
    ) {
        Text(text = "Iniciar Sesión")
    }
}

/*
@Composable
fun ForgotPassword(modifier: Modifier) {
    Text(
        text = "Registrar",
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF4EA8E9),
        modifier = modifier
    )
}

*/


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Password(password: String, onTextChanged: (String) -> Unit) {
    var passwordVisibility by remember {
        mutableStateOf(false)
    }

    TextField(
        value = password,
        label = { Text(text = "Contraseña") },
        onValueChange = { onTextChanged(it) },
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = "Ingrese contraseña") },
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        colors = TextFieldDefaults.run {
            textFieldColors(
                containerColor = Color(0xFFE7F0F7),
                textColor = Color(0xFF992E2E),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            )
        },
        leadingIcon = {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = "icon usuario",
                )
            }
        },
        visualTransformation = if (passwordVisibility) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
        trailingIcon = {
            val image = if (passwordVisibility) {
                Icons.Filled.VisibilityOff
            } else {
                Icons.Filled.Visibility
            }
            IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                Icon(imageVector = image, contentDescription = "visibilidad de password")
            }
        })

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Email(email: String, onTextChanged: (String) -> Unit) {

    TextField(
        value = email,
        label = { Text(text = "Usuario") },
        onValueChange = { onTextChanged(it) },
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = "Nombre de la farmacia") },
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
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "icon usuario",
                )
            }
        }
    )
}

@Composable
fun ImageLogo(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.logo_footer),
        contentDescription = "logo",
        modifier = modifier.size(190.dp)
    )
}


@Composable
fun Header(modifier: Modifier) {
    val activity = LocalContext.current as Activity
    Icon(imageVector = Icons.Default.Close,
        contentDescription = "close app",
        modifier = modifier.clickable { activity.finish() })
}
