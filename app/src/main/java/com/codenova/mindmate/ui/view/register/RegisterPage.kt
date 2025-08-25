package com.codenova.mindmate.ui.view.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.codenova.mindmate.R
import com.codenova.mindmate.ui.components.AppLoader
import com.codenova.mindmate.ui.components.AppTextButton
import com.codenova.mindmate.ui.theme.LARGE_PADDING
import com.codenova.mindmate.ui.theme.MEDIUM_PADDING
import com.codenova.mindmate.ui.theme.MindMateTheme

@Composable
fun RegisterPage(
    onNavigateToBottomNavGraph: () -> Unit,
    viewModel: RegisterViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    // collect navigation event
    LaunchedEffect(key1 = Unit) {
        viewModel.navigateHome.collect {
            onNavigateToBottomNavGraph() // trigger navigation
        }
    }

    Scaffold{ innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(vertical = LARGE_PADDING, horizontal = MEDIUM_PADDING)
                .fillMaxSize()
        ) {
            Text(
                text = stringResource(id = R.string.register_title),
                style = MaterialTheme.typography.headlineLarge.copy(
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Black,
                    fontSize = 40.sp
                )
            )

            Text(
                text = stringResource(id = R.string.register_subtitle),
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

            RegisterForm(
                email = (uiState as? RegisterUiState.Editing)?.email ?: "",
                emailError = (uiState as? RegisterUiState.Editing)?.emailError ?: null,
                password = (uiState as? RegisterUiState.Editing)?. password ?: "",
                passwordError = (uiState as? RegisterUiState.Editing)?. passwordError ?: null,
                onEmailChange = viewModel::onEmailChange,
                onPasswordChange = viewModel::onPasswordChange,
                checked = (uiState as? RegisterUiState.Editing)?.keepLoggedIn ?: false,
                onCheckedChange = viewModel::onKeepLoggedInChange,
                onRegisterClick = { viewModel.register()}
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.register_already_have_account),
                    style = MaterialTheme.typography.bodyMedium
                )

                AppTextButton(
                    text = stringResource(id = R.string.register_login),
                    onClick = {}
                )
            }
        }
    }

    if(uiState is RegisterUiState.Loading) {
        AppLoader(
            isLoading = uiState is RegisterUiState.Loading,
            title = stringResource(id = R.string.register_loading_title),
            message = stringResource(id = R.string.register_loading_subtitle)
        )
    }
}

@Composable
@Preview(showBackground = true)
fun RegisterPagePreview() {
    MindMateTheme {
        RegisterPage(
            onNavigateToBottomNavGraph = TODO(),
            viewModel = TODO()
        )
    }
}