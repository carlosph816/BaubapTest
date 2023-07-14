package com.caperezh.baubaptest.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.caperezh.baubaptest.R
import com.caperezh.baubaptest.presentation.viewmodel.LoginState
import com.caperezh.baubaptest.presentation.viewmodel.LoginViewModel

@Composable
fun LoginView(
    showSnackBar: (String) -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val loginState by viewModel.loginState.collectAsState()

    Box(modifier = Modifier.fillMaxSize().padding()) {
        when (loginState) {
            is LoginState.Error -> {
                showSnackBar((loginState as LoginState.Error).message)
            }
            is LoginState.Success -> {
                if (viewModel.showDialog) {
                    AlertDialog(
                        onDismissRequest = { viewModel.showDialog},
                        title = { Text(stringResource(R.string.baubap)) },
                        text = { Text(stringResource(R.string.se_ha_logueado_correctamente)) },
                        confirmButton = {
                            Button(
                                onClick = { viewModel.dismissDialog()}
                            ) {
                                Text(stringResource(R.string.aceptar))
                            }
                        }
                    )
                }
            }
            else -> {}
        }
        if (viewModel.showLoader){
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(20.dp)
            )
        }
        LoginContent(
            onClickLogin = {
                viewModel.login()
            }
        )
    }
}

@Composable
fun LoginContent(
    onClickLogin: () -> Unit
) {
    Column(
        modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val username = remember { mutableStateOf(TextFieldValue()) }
        val password = remember { mutableStateOf(TextFieldValue()) }

        Image(
            painter = painterResource(id = R.drawable.logo_baubap),
            contentDescription = stringResource(id = R.string.logo_baubap),
            contentScale = ContentScale.FillHeight
        )
        Text(
            text = "Login",
            style = TextStyle(fontSize = 40.sp, fontFamily = FontFamily.SansSerif)
        )

        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            label = { Text(text = stringResource(R.string.username)) },
            value = username.value,
            onValueChange = { username.value = it })

        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            label = { Text(text = stringResource(R.string.password)) },
            value = password.value,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            onValueChange = { password.value = it })

        Spacer(modifier = Modifier.height(20.dp))
        Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
            Button(
                onClick = onClickLogin,
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(text = stringResource(R.string.login))
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
    }
}
