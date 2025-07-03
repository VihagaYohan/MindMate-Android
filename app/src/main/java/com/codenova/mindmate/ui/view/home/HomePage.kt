package com.codenova.mindmate.ui.view.home

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
fun HomePage(onNavigateToProfile: () -> Unit) {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Welcome to MindMate!")
            Button(onClick = {onNavigateToProfile()}) {
                Text(text = "Go to profile page")
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun HomePagePreview() {
    MindMateTheme {
        HomePage(onNavigateToProfile = {})
    }
}