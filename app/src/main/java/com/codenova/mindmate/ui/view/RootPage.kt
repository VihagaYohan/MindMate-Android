package com.codenova.mindmate.ui.view

import android.R.attr.contentDescription
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.NavigationRailItemColors
import androidx.compose.material3.NavigationRailItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteColors
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.window.core.layout.WindowSizeClass
import com.codenova.mindmate.navigation.BottomAppBarDestinations
import com.codenova.mindmate.ui.theme.MindMateTheme
import com.codenova.mindmate.ui.theme.SMALL_PADDING
import com.codenova.mindmate.ui.view.home.HomePage
import com.codenova.mindmate.ui.view.journal.JournalPage
import com.codenova.mindmate.ui.view.mood.MoodPage
import com.codenova.mindmate.ui.view.profile.ProfilePage

@Composable
fun RootPage() {
    var currentDestination by rememberSaveable {
        mutableStateOf(BottomAppBarDestinations.Home)
    }

    val navigationSuiteItemColors = NavigationSuiteDefaults.itemColors(
        navigationBarItemColors = NavigationBarItemDefaults.colors(
            indicatorColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f),
            selectedIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
            selectedTextColor = MaterialTheme.colorScheme.onPrimaryContainer
        ),
        navigationRailItemColors = NavigationRailItemDefaults.colors(
            indicatorColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f),
            selectedIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
            selectedTextColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    )

    NavigationSuiteScaffold(
        navigationSuiteItems = {
            BottomAppBarDestinations.entries.forEach { it ->
                item(
                    icon = {
                        if(it == currentDestination) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Icon(
                                    imageVector = it.filledIcon,
                                    contentDescription = stringResource(id = it.contentDescription))

                                Spacer(
                                    modifier = Modifier.width(width = SMALL_PADDING)
                                )

                                Text(
                                    text = stringResource(id = it.label),
                                    style = MaterialTheme.typography.labelSmall,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        } else {
                            Icon(
                                imageVector = it.thinIcon,
                                contentDescription = stringResource(id = it.contentDescription))
                        }
                    },
                    selected = it == currentDestination,
                    onClick = {
                        currentDestination = it
                    },
                    colors = navigationSuiteItemColors,
                    label = {},
                    alwaysShowLabel = false
                )
            }
        },
        navigationSuiteColors = NavigationSuiteDefaults.colors(
            navigationBarContainerColor = MaterialTheme.colorScheme.background,
            navigationBarContentColor = MaterialTheme.colorScheme.onBackground
        )
    ) {
        // destination content
        when(currentDestination) {
            BottomAppBarDestinations.Home -> HomePage(
                onNavigateToProfile =  {
                    
                }
            )
            BottomAppBarDestinations.Journal -> JournalPage()
            BottomAppBarDestinations.Mood -> MoodPage()
            BottomAppBarDestinations.Profile -> ProfilePage(
                name = "Jane Smith",
                onNavigateToHome = {}
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun RootPagePreview() {
    MindMateTheme { 
        RootPage()
    }
}