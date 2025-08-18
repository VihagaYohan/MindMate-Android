package com.codenova.mindmate.ui.view.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import com.codenova.mindmate.ui.components.AppInputField
import com.codenova.mindmate.ui.theme.MindMateTheme

@Composable
fun LoginForm(
    email: String,
    emailError: String?,
    password: String,
    passwordError: String?,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        AppInputField(
            value = email,
            placeHolder = "Enter your email",
            label = "Email",
            isError = {emailError != null},
            errorMessage = emailError,
            onValueChange = { it -> onEmailChange(it)}
        )

        AppInputField(
            value = password,
            placeHolder = "Enter your password",
            label = "Password",
            isError = {passwordError != null},
            errorMessage = passwordError,
            onValueChange = { it -> onPasswordChange(it)}
        )
    }
}

@Composable
@Preview
fun LoginFormPreview() {
    MindMateTheme {
        LoginForm(
            email = "",
            emailError = null,
            password = "",
            passwordError = null,
            onEmailChange = {},
            onPasswordChange = {}
        )
    }
}