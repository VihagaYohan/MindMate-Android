package com.codenova.mindmate.ui.view.login

import android.R.attr.checked
import android.widget.CheckBox
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codenova.mindmate.ui.components.AppInputField
import com.codenova.mindmate.ui.theme.MindMateTheme
import com.codenova.mindmate.ui.theme.SMALL_PADDING
import com.codenova.mindmate.R
import com.codenova.mindmate.ui.components.AppButton

@Composable
fun LoginForm(
    email: String,
    emailError: String?,
    password: String,
    passwordError: String?,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    checked: Boolean = false,
    onCheckedChange: (Boolean) -> Unit = {}
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        AppInputField(
            value = email,
            placeHolder = stringResource(id = R.string.enter_email_address),
            label = stringResource(id = R.string.email_address),
            isError = {emailError != null},
            errorMessage = emailError,
            onValueChange = { it -> onEmailChange(it)}
        )

        Spacer(
            modifier = Modifier
                .height(height = SMALL_PADDING)
        )

        AppInputField(
            value = password,
            placeHolder = stringResource(id = R.string.enter_password),
            label = stringResource(id = R.string.password),
            isError = {passwordError != null},
            errorMessage = passwordError,
            onValueChange = { it -> onPasswordChange(it)}
        )

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
           Row(
               verticalAlignment = Alignment.CenterVertically,
               horizontalArrangement = Arrangement.Start
           ) {
               Checkbox(
                   checked = checked,
                   onCheckedChange = {it -> onCheckedChange(it)}
               )

               Text(
                   text = stringResource(id = R.string.keep_me_logged_in),
                   style = MaterialTheme.typography.bodyMedium
               )
           }

            Text(
                text = stringResource(id = R.string.forgot_password),
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.SemiBold
                )
            )
        }

        AppButton(
            label = stringResource(id = R.string.login_button),
            onClick = {}
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