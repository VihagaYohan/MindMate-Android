package com.codenova.mindmate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.codenova.mindmate.navigation.AppNavigation
import com.codenova.mindmate.navigation.Screen
import com.codenova.mindmate.ui.theme.MindMateTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MindMateTheme {
                // nav controller
                val navController = rememberNavController()

                AppNavigation(
                    navController = navController,
                    startDestination = Screen.Login)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MindMateTheme {

    }
}