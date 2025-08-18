package com.codenova.mindmate.ui.view.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.adamglin.PhosphorIcons
import com.adamglin.phosphoricons.Bold
import com.adamglin.phosphoricons.bold.Envelope
import com.adamglin.phosphoricons.bold.Lock
import com.adamglin.phosphoricons.bold.Mailbox
import com.codenova.mindmate.ui.components.AppInputField
import com.codenova.mindmate.ui.theme.MindMateTheme
import com.codenova.mindmate.ui.theme.SMALL_PADDING
import com.codenova.mindmate.R
import com.codenova.mindmate.ui.components.AppButton
import com.codenova.mindmate.ui.components.AppTextButton
import com.codenova.mindmate.ui.theme.LARGE_PADDING
import com.codenova.mindmate.ui.theme.MEDIUM_PADDING
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.adamglin.phosphoricons.Thin
import com.adamglin.phosphoricons.thin.Envelope
import com.adamglin.phosphoricons.thin.Eye
import com.adamglin.phosphoricons.thin.EyeSlash

@Composable
fun LoginForm(
    email: String,
    emailError: String?,
    password: String,
    passwordError: String?,
    checked: Boolean = false,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onCheckedChange: (Boolean) -> Unit = {},
    onLoginClick: () -> Unit = {}
) {
    var passwordVisible by remember {
        mutableStateOf(false)
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        // email
        AppInputField(
            value = email,
            placeHolder = stringResource(id = R.string.enter_email_address),
            label = stringResource(id = R.string.email_address),
            isError = { emailError != null },
            errorMessage = emailError,
            onValueChange = { it -> onEmailChange(it) },
            trailingIcon = {
                Icon(
                    imageVector = PhosphorIcons.Thin.Envelope,
                    contentDescription = null
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
        )

        Spacer(
            modifier = Modifier
                .height(height = SMALL_PADDING)
        )

        // password
        AppInputField(
            value = password,
            placeHolder = stringResource(id = R.string.enter_password),
            label = stringResource(id = R.string.password),
            isError = { passwordError != null },
            errorMessage = passwordError,
            onValueChange = { it -> onPasswordChange(it) },
            trailingIcon = {
                Icon(
                    imageVector = if (passwordVisible) PhosphorIcons.Thin.Eye else PhosphorIcons.Thin.EyeSlash,
                    contentDescription = ""
                )
            },
            onTrailingIconClick = {
                passwordVisible = !passwordVisible
            },
            visualTransformation = if (passwordVisible) VisualTransformation.None else
                PasswordVisualTransformation(mask = '*'),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
        )

        // keep logged in and forgot password
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(MEDIUM_PADDING)
            ) {
                Box(
                    modifier = Modifier
                        .size(20.dp)
                        .clip(RoundedCornerShape(4.dp))
                ) {
                    Checkbox(
                        checked = checked,
                        onCheckedChange = { it -> onCheckedChange(it) },
                        colors = CheckboxDefaults.colors(
                            uncheckedColor = MaterialTheme.colorScheme.primary,
                        )
                    )
                }

                Text(
                    text = stringResource(id = R.string.keep_me_logged_in),
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            AppTextButton(
                text = stringResource(id = R.string.forgot_password),
                onClick = {}
            )
        }

        Spacer(
            modifier = Modifier
                .height(height = LARGE_PADDING * 2)
        )

        AppButton(
            label = stringResource(id = R.string.login_button),
            onClick = { onLoginClick() }
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