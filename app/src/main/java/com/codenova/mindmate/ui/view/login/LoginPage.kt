package com.codenova.mindmate.ui.view.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.codenova.mindmate.ui.theme.MindMateTheme

@Composable
fun LoginPage(onNavigateToBottomNavGraph: () -> Unit) {
    val loginViewModel: LoginViewModel = hiltViewModel()
    val uiState = loginViewModel.uiState.collectAsState()

    var email by remember { mutableStateOf("vihagayohan94@gmail.com") }
    var password by remember {mutableStateOf("Batman")}

    Scaffold{ innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Login page")

            OutlinedTextField(
                value = email,
                onValueChange = {email = it},
                label = {
                    Text(text = "Email")
                },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = password,
                onValueChange = {
                    password = it
                },
                label = {
                    Text(text = "Password")
                },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                loginViewModel.login(email.trim(), password.trim())
            }) {
                if(uiState is LoginUiState.Loading){
                    CircularProgressIndicator(
                        modifier = Modifier.padding(20.dp),
                        color = MaterialTheme.colorScheme.primary,
                        strokeWidth = 2.dp
                    )
            }
                Text(text = "Login")
            }

            if(uiState is LoginUiState.Error) {
                Text(text = (uiState as LoginUiState.Error).message)
            }

            if(uiState is LoginUiState.Success) {
                Column {
                    Text(
                        text = "${(uiState as LoginUiState.Success).authTokens.accessToken}"
                    )
                    Text(
                        text = "${(uiState as LoginUiState.Success).authTokens.refreshToken}"
                    )
                }
            }

            Button(onClick = {onNavigateToBottomNavGraph()}) {
                Text(text = "Go to register page")
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun LoginPagePreview() {
    MindMateTheme {
        LoginPage(onNavigateToBottomNavGraph = {})
    }
}