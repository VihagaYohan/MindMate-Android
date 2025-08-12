package com.codenova.mindmate.ui.theme

import androidx.compose.ui.text.font.Font
import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.codenova.mindmate.R

val RobotoFontFamily = FontFamily(
    Font(R.font.roboto_regular),
    Font(resId = R.font.roboto_bold),
    Font(resId = R.font.roboto_semibold),
    Font(resId = R.font.roboto_thin),
    Font(resId = R.font.roboto_italic, style = FontStyle.Italic),
    Font(resId = R.font.roboto_medium),
    Font(resId = R.font.roboto_light)
)

val PoppinsFontFamily = FontFamily(
    Font(resId = R.font.poppins_regular),
    Font(resId = R.font.poppins_bold),
    Font(resId = R.font.poppins_semibold),
    Font(resId = R.font.poppins_thin),
    Font(resId = R.font.poppins_italic, style = FontStyle.Italic),
    Font(resId = R.font.poppins_medium),
    Font(resId = R.font.roboto_light)
)

// Set of Material typography styles to start with
val Typography = Typography(
/*    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),*/
    displayLarge = TextStyle(
        fontFamily = PoppinsFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 57.sp
    ), // ✅ Splash title / main branding

    displayMedium = TextStyle(
        fontFamily = PoppinsFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 45.sp
    ), // ✅ Major section titles

    displaySmall = TextStyle(
        fontFamily = PoppinsFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 36.sp
    ), // ✅ Onboarding headers / big titles

    headlineLarge = TextStyle(
        fontFamily = PoppinsFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 32.sp
    ), // ✅ Main screen titles

    headlineMedium = TextStyle(
        fontFamily = PoppinsFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 28.sp
    ), // ✅ Card / section headers

    headlineSmall = TextStyle(
        fontFamily = PoppinsFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp
    ), // ✅ Smaller screen headers

    titleLarge = TextStyle(
        fontFamily = PoppinsFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 22.sp
    ), // ✅ Dialog titles, screen sections

    titleMedium = TextStyle(
        fontFamily = PoppinsFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp
    ), // ✅ Button labels / input labels

    titleSmall = TextStyle(
        fontFamily = PoppinsFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp
    ), // ✅ Field labels / chip text

    bodyLarge = TextStyle(
        fontFamily = PoppinsFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ), // ✅ Main body text / paragraphs

    bodyMedium = TextStyle(
        fontFamily = PoppinsFontFamily,
        fontWeight = FontWeight.Light,
        fontSize = 14.sp
    ), // ✅ Supporting text / form hints

    bodySmall = TextStyle(
        fontFamily = PoppinsFontFamily,
        fontWeight = FontWeight.Thin,
        fontSize = 12.sp
    ), // ✅ Captions / timestamps

    labelLarge = TextStyle(
        fontFamily = PoppinsFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp
    ), // ✅ Button and icon labels

    labelMedium = TextStyle(
        fontFamily = PoppinsFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp
    ), // ✅ Toggle switches, tabs, filters

    labelSmall = TextStyle(
        fontFamily = PoppinsFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp
    ) // ✅ Helper text, tips, or metadata

    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)