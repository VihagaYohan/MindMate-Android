package com.codenova.mindmate.ui.view.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.codenova.mindmate.ui.theme.MindMateTheme

@Composable
fun LoginPage(
    onNavigateToBottomNavGraph: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel()) {

    // val viewModel: LoginViewModel = hiltViewModel();
    val uiState by viewModel.uiState.collectAsState()

    Scaffold{ innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            LoginForm(
                email = (uiState as? LoginUiState.Editing)?.email ?: "",
                emailError = (uiState as? LoginUiState.Editing)?.emailError ?: null,
                password = (uiState as? LoginUiState.Editing)?. password ?: "",
                passwordError = (uiState as? LoginUiState.Editing)?. passwordError ?: null,
                onEmailChange = viewModel::onEmailChange,
                onPasswordChange = viewModel::onPasswordChange
            )
        }
    }
}
@Composable
@Preview(showBackground = true)
fun LoginPagePreview() {
    MindMateTheme {
        LoginPage(onNavigateToBottomNavGraph = {},
            viewModel = hiltViewModel())
    }
}