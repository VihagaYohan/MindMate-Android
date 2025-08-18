package com.codenova.mindmate.ui.view.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.codenova.mindmate.ui.theme.MindMateTheme
import com.codenova.mindmate.R
import com.codenova.mindmate.ui.theme.LARGE_PADDING
import com.codenova.mindmate.ui.theme.MEDIUM_PADDING

@Composable
fun LoginPage(
    onNavigateToBottomNavGraph: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsState()

    Scaffold{ innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(vertical = LARGE_PADDING, horizontal = MEDIUM_PADDING)
                .fillMaxSize(),
        ) {

            Text(
                text = stringResource(id = R.string.login_account),
                style = MaterialTheme.typography.headlineLarge.copy(
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Black,
                    fontSize = 40.sp
                )
            )

            Text(
                text = stringResource(id = R.string.welcome_back),
                style = MaterialTheme.typography.headlineMedium.copy(
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp
                )
            )

            Spacer(
                modifier = Modifier
                    .height(height = LARGE_PADDING * 4)
            )

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
            viewModel = hiltViewModel()
        )
    }
}