package com.codenova.mindmate.ui.view.journal

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.codenova.mindmate.ui.theme.MindMateTheme

@Composable
fun JournalPage() {
    Scaffold{innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Journal page")
        }
    }
}

@Composable
@Preview(showBackground = true)
fun JournalPagePreview() {
    MindMateTheme {
        JournalPage()
    }
}