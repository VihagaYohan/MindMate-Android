package com.codenova.mindmate.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lock
import androidx.compose.ui.graphics.vector.ImageVector
import com.adamglin.PhosphorIcons
import com.adamglin.phosphoricons.Fill
import com.adamglin.phosphoricons.Thin
import com.adamglin.phosphoricons.fill.House
import com.adamglin.phosphoricons.fill.NotePencil
import com.adamglin.phosphoricons.fill.Smiley
import com.adamglin.phosphoricons.fill.User
import com.adamglin.phosphoricons.thin.House
import com.adamglin.phosphoricons.thin.NotePencil
import com.adamglin.phosphoricons.thin.Smiley
import com.adamglin.phosphoricons.thin.User
import com.codenova.mindmate.R

enum class BottomAppBarDestinations(
    @StringRes val label: Int,
    val thinIcon: ImageVector,
    val filledIcon: ImageVector,
    @StringRes val contentDescription: Int
) {
    Home(
        label = R.string.bottom_home_destination,
        thinIcon = PhosphorIcons.Thin.House,
        filledIcon = PhosphorIcons.Fill.House,
        contentDescription = R.string.bottom_home_destination
    ),
    Journal(
        label = R.string.bottom_journal_destination,
        thinIcon = PhosphorIcons.Thin.NotePencil,
        filledIcon = PhosphorIcons.Fill.NotePencil,
        contentDescription = R.string.bottom_journal_destination
    ),
    Mood(
        label = R.string.bottom_mood_destination,
        thinIcon = PhosphorIcons.Thin.Smiley,
        filledIcon = PhosphorIcons.Fill.Smiley,
        contentDescription = R.string.bottom_mood_destination
    ),
    Profile(
        label = R.string.bottom_profile_destination,
        thinIcon = PhosphorIcons.Thin.User,
        filledIcon = PhosphorIcons.Fill.User,
        contentDescription = R.string.bottom_profile_destination
    )
}