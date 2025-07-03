package com.codenova.mindmate.ui.view.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.codenova.mindmate.ui.theme.MindMateTheme

@Composable
fun LoginPage() {
    Scaffold{ innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Login page")
            Button(onClick = {}) {
                Text(text = "Go to register page")
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun LoginPagePreview() {
    MindMateTheme {
        LoginPage()
    }
}