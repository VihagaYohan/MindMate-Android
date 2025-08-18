package com.codenova.mindmate.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.codenova.mindmate.ui.theme.MindMateTheme
import androidx.compose.material3.Text
import androidx.compose.ui.text.font.FontWeight

@Composable
fun AppTextButton(
    text: String,
    onClick:() -> Unit
) {
    TextButton(
        onClick = {onClick}
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Bold
            )
        )
    }
}

@Composable
@Preview(showBackground = true)
fun AppTextButtonPreview() {
    MindMateTheme {
        AppTextButton(
         text = "text button", onClick = {})
    }
}