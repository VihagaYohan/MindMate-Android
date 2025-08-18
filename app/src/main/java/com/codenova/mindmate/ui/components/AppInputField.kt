package com.codenova.mindmate.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.codenova.mindmate.ui.theme.MindMateTheme

@Composable
fun AppInputField(
    value: String = "",
    placeHolder: String = "",
    label: String = "",
    onValueChange: (String) -> Unit,
    isError: () -> Boolean = { false },
    errorMessage: String? = ""
) {

    OutlinedTextField(
        value = value,
        onValueChange = { it -> onValueChange(it) },
        label = { Text(text = label) },
        placeholder = { Text(text = placeHolder) },
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
        }
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
            isError = {true},
            errorMessage = "error"
        )
    }
}