package com.codenova.mindmate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.codenova.mindmate.ui.theme.MindMateTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MindMateTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Column {
        Text(
            text = "Hello $name!",
            style = MaterialTheme.typography.bodyLarge,
            modifier = modifier
        )
        Button(onClick = {}) {
            Text(text = "Login",
                style = MaterialTheme.typography.bodyMedium)
        }
        Text(
            text = "Hello $name!",
            style = MaterialTheme.typography.bodyLarge,
            modifier = modifier
        )
        Text(
            text = "Hello $name!",
            style = MaterialTheme.typography.bodyMedium,
            modifier = modifier
        )
        Text(
            text = "Hello $name!",
            style = MaterialTheme.typography.bodySmall,
            modifier = modifier
        )

        Text(
            text = "Hello $name!",
            style = MaterialTheme.typography.labelLarge,
            modifier = modifier
        )
        Text(
            text = "Hello $name!",
            style = MaterialTheme.typography.labelMedium,
            modifier = modifier
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MindMateTheme {
        Greeting("Android")
    }
}