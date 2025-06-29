package com.codenova.mindmate.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = md_theme_dark_primary, // ✅ Brand color adapted for dark theme
    onPrimary = md_theme_dark_onPrimary, // Text/icon on dark primary

    primaryContainer = md_theme_dark_primaryContainer, // Card/button backgrounds
    onPrimaryContainer = md_theme_dark_onPrimaryContainer, // Text/icon inside primary container

    secondary = md_theme_dark_secondary, // Accent for badges, chips, tabs
    onSecondary = md_theme_dark_onSecondary, // Text/icon on secondary

    secondaryContainer = md_theme_dark_secondaryContainer, // Background of inputs or tabs
    onSecondaryContainer = md_theme_dark_onSecondaryContainer, // Text/icon inside secondary container

    background = md_theme_dark_background, // App background (dark)
    onBackground = md_theme_dark_onBackground, // Text/icons on dark background

    surface = md_theme_dark_surface, // Cards/sheets/dialogs (dark)
    onSurface = md_theme_dark_onSurface, // Text/icons on dark surfaces

    error = md_theme_dark_error, // Error background
    onError = md_theme_dark_onError, // Text/icon on error

    tertiary = md_theme_dark_tertiary, // Additional emotional accent (alerts, callouts)
    onTertiary = md_theme_dark_onTertiary // Text/icon on tertiary surface
)

private val LightColorScheme = lightColorScheme(
    primary = md_theme_light_primary, // ✅ Brand color for buttons, highlights
    onPrimary = md_theme_light_onPrimary, // Text/icon on top of primary buttons

    primaryContainer = md_theme_light_primaryContainer, // Background for cards or elevated buttons
    onPrimaryContainer = md_theme_light_onPrimaryContainer, // Text/icon inside primary container

    secondary = md_theme_light_secondary, // Accent color for chips, badges, or supporting elements
    onSecondary = md_theme_light_onSecondary, // Text/icon on secondary elements

    secondaryContainer = md_theme_light_secondaryContainer, // Background for chips or input fields
    onSecondaryContainer = md_theme_light_onSecondaryContainer, // Text/icon on secondary container

    background = md_theme_light_background, // App background
    onBackground = md_theme_light_onBackground, // Primary text/icons on background

    surface = md_theme_light_surface, // Default surface (cards, sheets, dialogs)
    onSurface = md_theme_light_onSurface, // Text/icons on surfaces

    error = md_theme_light_error, // Error state (inputs, snackbar)
    onError = md_theme_light_onError, // Text/icon on error surface

    tertiary = md_theme_light_tertiary, // Additional accent (emotion markers, positive callouts)
    onTertiary = md_theme_light_onTertiary // Text/icon on tertiary backgrounds

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun MindMateTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}