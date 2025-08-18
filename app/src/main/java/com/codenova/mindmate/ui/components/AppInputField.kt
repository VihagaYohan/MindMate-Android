package com.codenova.mindmate.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codenova.mindmate.ui.theme.MindMateTheme

@Composable
fun AppInputField(
    value: String = "",
    placeHolder: String = "",
    label: String = "",
    errorMessage: String? = "",
    leadingIcon: @Composable() (() -> Unit)? = null,
    trailingIcon: @Composable() (() -> Unit)? = null,
    isError: () -> Boolean = { false },
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    onValueChange: (String) -> Unit,
    onTrailingIconClick: (() -> Unit)? = null
) {

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth(),
        value = value,
        onValueChange = { it -> onValueChange(it) },
        label = {
            Text(
                text = label,
                style = MaterialTheme.typography.labelMedium
            )
        },
        placeholder = {
            Text(
                text = placeHolder,
                style = MaterialTheme.typography.labelMedium
            )
        },
        isError = isError(),
        supportingText = {
            if (isError() && errorMessage != null) {
                Text(
                    text = errorMessage,
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = MaterialTheme.colorScheme.error
                    )
                )
            }
        },
        shape = RoundedCornerShape(10.dp),
        leadingIcon = leadingIcon,
        trailingIcon = {
            if(trailingIcon != null && onTrailingIconClick != null) {
                IconButton(
                    onClick = { onTrailingIconClick() }
                ) {
                    trailingIcon()
                }
            } else {
                // if trailing icon is not null but onTrailingIconClick is null, call it as a function
                // or else do nothing
                trailingIcon?.invoke()
            }
        },
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions
    )

}

@Composable
@Preview(showBackground = true)
fun AppInputFieldPreview() {
    MindMateTheme {
        AppInputField(
            value = "",
            placeHolder = "Enter something here",
            label = "Field label",
            onValueChange = {},
            isError = { true },
            errorMessage = "error"
        )
    }
}