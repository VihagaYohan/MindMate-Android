package com.codenova.mindmate.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codenova.mindmate.ui.theme.MindMateTheme

@Composable
fun Avatar(name: String, imageUrl: String? = null) {
    val gradientBrush = Brush.horizontalGradient(
        colors = listOf(MaterialTheme.colorScheme.primary, MaterialTheme.colorScheme.secondary),
        startX = 0f,
        endX = 100f
    )
    Box(
        modifier = Modifier
            .size(40.dp)
            .background(
                brush = gradientBrush,
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = name.split(" ").joinToString("") { it -> it.take(1) },
            style = MaterialTheme.typography.bodyMedium,
            color = Color.White
        )
    }
}

@Composable
@Preview(showBackground = true)
fun AvatarPreview() {
    MindMateTheme {
        Avatar(name = "John Doe")
    }
}