package com.codenova.mindmate.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.codenova.mindmate.ui.modifiers.flexibleWidth
import com.codenova.mindmate.ui.theme.MindMateTheme

@Composable
fun AppButton(
    label: String,
    onClick: () -> Unit,
    isPrimary: Boolean? = true,
    width: Dp? = null
) {
    if(isPrimary == true) {
        Button(
            modifier = Modifier
                .flexibleWidth(width),
            onClick = onClick) {
            Text(text = label)
        }
    } else {
        OutlinedButton(
            modifier = Modifier
                .flexibleWidth(width),
            onClick = onClick,
            border = BorderStroke(
                width = 1.dp,
                color = MaterialTheme.colorScheme.primary
            ),
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = Color.White
            )
        ) {
            Text(text = label)
        }
    }
}

@Composable
@Preview(showBackground = true)
fun AppButtonPrimaryPreview() {
    MindMateTheme {
        AppButton(
            label = "Button label",
            onClick = {},
        )
    }
}

@Composable
@Preview(showBackground = true)
fun AppButtonSecondaryPreview() {
    MindMateTheme {
        AppButton(
            label = "Button label",
            onClick = {},
            isPrimary = false
        )
    }
}