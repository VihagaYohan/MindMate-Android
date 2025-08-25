package com.codenova.mindmate.ui.view.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.adamglin.PhosphorIcons
import com.adamglin.phosphoricons.Thin
import com.adamglin.phosphoricons.thin.Envelope
import com.adamglin.phosphoricons.thin.Eye
import com.adamglin.phosphoricons.thin.EyeSlash
import com.codenova.mindmate.ui.components.AppInputField
import com.codenova.mindmate.R
import com.codenova.mindmate.ui.components.AppButton
import com.codenova.mindmate.ui.theme.LARGE_PADDING
import com.codenova.mindmate.ui.theme.MEDIUM_PADDING
import com.codenova.mindmate.ui.theme.MindMateTheme
import com.codenova.mindmate.ui.theme.SMALL_PADDING

@Composable
fun RegisterForm(
    email: String,
    emailError: String?,
    password: String,
    passwordError: String?,
    checked: Boolean = false,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onRegisterClick: () -> Unit = {},
    onCheckedChange: (Boolean) -> Unit = {},
) {
    var passwordVisible by remember {
        mutableStateOf(false)
    }

    // email
    AppInputField(
        value = email,
        placeHolder = stringResource(id = R.string.register_email_placeholder),
        label = stringResource(id = R.string.register_email_field),
        isError = { emailError != null },
        errorMessage = emailError,
        onValueChange = { it -> onEmailChange(it)},
        trailingIcon = {
            Icon(
                imageVector = PhosphorIcons.Thin.Envelope,
                contentDescription = stringResource(id = R.string.register_email_field),
                tint = if (emailError != null) MaterialTheme.colorScheme.error else
                MaterialTheme.colorScheme.onBackground
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next
        )
    )

    Spacer(
        modifier = Modifier
            .height(height = SMALL_PADDING)
    )

    // password
    AppInputField(
        value = password,
        placeHolder = stringResource(id = R.string.register_password_placeholder),
        label = stringResource(id = R.string.register_password_field),
        isError = { passwordError != null},
        errorMessage = passwordError,
        onValueChange = { it -> onPasswordChange(it)},
        trailingIcon = {
            Icon(
                imageVector = if(passwordVisible) PhosphorIcons.Thin.Eye else
                PhosphorIcons.Thin.EyeSlash,
                contentDescription = stringResource(id = R.string.register_password_field),
                tint = if(passwordError != null) MaterialTheme.colorScheme.error else
                MaterialTheme.colorScheme.onBackground,
            )
        },
        onTrailingIconClick = {
            passwordVisible = !passwordVisible
        },
        visualTransformation = if(passwordVisible) VisualTransformation.None else PasswordVisualTransformation(mask = '*'),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done
        )
    )

    // keep logged in
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(MEDIUM_PADDING)
    ) {
        Box(
            modifier = Modifier
                .size(20.dp)
                .clip(shape = RoundedCornerShape(4.dp))
        ) {
            Checkbox(
                checked = checked,
                onCheckedChange = { it -> onCheckedChange(it)},
                colors = CheckboxDefaults.colors(
                    uncheckedColor = MaterialTheme.colorScheme.primary
                )
            )
        }
        Text(
            text = stringResource(id = R.string.register_keep_me_logged_in),
            style = MaterialTheme.typography.bodyLarge
        )
    }

    Spacer(
        modifier = Modifier
            .height(height = LARGE_PADDING * 2)
    )

    AppButton(
        label = stringResource(id = R.string.register_button),
        onClick = { onRegisterClick() }
    )
}

@Composable
@Preview(showBackground = true)
fun RegisterFormPreview() {
    MindMateTheme {
        RegisterForm(
            email = "",
            emailError = null,
            password = "",
            passwordError = null,
            onEmailChange = {},
            onPasswordChange = {}
        )
    }
}

