package com.codenova.mindmate.ui.view.home

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.adamglin.PhosphorIcons
import com.adamglin.phosphoricons.Bold
import com.adamglin.phosphoricons.Thin
import com.adamglin.phosphoricons.bold.Bell
import com.adamglin.phosphoricons.bold.BellRinging
import com.adamglin.phosphoricons.bold.BellSimpleSlash
import com.adamglin.phosphoricons.bold.Notification
import com.adamglin.phosphoricons.thin.Bell
import com.codenova.mindmate.ui.theme.MindMateTheme
import com.codenova.mindmate.ui.theme.SMALL_PADDING
import com.codenova.mindmate.R
import com.codenova.mindmate.ui.components.Avatar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(onNavigateToProfile: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        // greeting and user name
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.Start
                        ) {
                            Text(
                                text = stringResource(id = R.string.welcome_title),
                                style = MaterialTheme.typography.bodyLarge
                            )
                            Text(
                                text = "John Doe",
                                style = MaterialTheme.typography.headlineLarge,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                },
                navigationIcon = {
                    Avatar(name = "John Doe")
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = PhosphorIcons.Bold.Bell,
                            contentDescription = stringResource(id = R.string.notification_icon_button)
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
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