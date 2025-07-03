package com.codenova.mindmate.ui.view.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.codenova.mindmate.ui.theme.MindMateTheme

@Composable
fun ProfilePage(name: String?, onNavigateToHome: () -> Unit) {

    val coroutineScope = rememberCoroutineScope()
    val viewModel: ProfileViewModel = viewModel()

    // observe navigation events
    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is ProfileUiEvent.NavigateToHome -> {
                    onNavigateToHome()
                }
            }
        }
    }

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Profile: $name")
            Button(onClick = {
                viewModel.onHomeClick()
            }) {
                Text(text = "Go to Home page")
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ProfilePagePreview() {
    MindMateTheme {
        ProfilePage(name = "John Doe", onNavigateToHome = {})
    }
}