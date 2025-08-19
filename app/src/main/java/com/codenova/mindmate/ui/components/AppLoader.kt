package com.codenova.mindmate.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codenova.mindmate.ui.theme.MindMateTheme
import com.codenova.mindmate.R
import com.codenova.mindmate.ui.theme.LARGE_PADDING
import com.codenova.mindmate.ui.theme.MEDIUM_PADDING
import com.codenova.mindmate.ui.theme.SMALL_PADDING

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppLoader(
    isLoading: Boolean = true,
    title: String? = null,
    message: String? = null
) {
    AnimatedVisibility(
        visible = isLoading,
        enter = slideInVertically(
            initialOffsetY = {fullHeight -> fullHeight},
            animationSpec = tween(durationMillis = 1000)
        ),
        exit = slideOutVertically(
            targetOffsetY = {fullHeight -> fullHeight},
            animationSpec = tween(durationMillis = 1000)
        )
    ) { }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .clickable(enabled = false) {},
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(LARGE_PADDING * 2),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(
                modifier = Modifier
                    .width(50.dp),
                color = MaterialTheme.colorScheme.primary,
                trackColor = MaterialTheme.colorScheme.onPrimary,
                strokeWidth = 5.dp
            )

            if(title != null && message != null) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(MEDIUM_PADDING),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    )

                    Text(
                        text = message,
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontStyle = FontStyle.Italic,
                            color = MaterialTheme.colorScheme.onBackground.copy(
                                alpha = 0.5f
                            )
                        ))
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun AppLoaderPreview() {
    MindMateTheme {
        AppLoader(
            isLoading = true,
            title = "Logging in...",
            message = "We're verifying your credentials"
        )
    }
}