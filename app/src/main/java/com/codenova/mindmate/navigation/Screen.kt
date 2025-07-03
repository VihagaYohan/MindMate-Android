package com.codenova.mindmate.navigation

import kotlinx.serialization.Serializable

sealed class Screen {
    @Serializable
    data object Home: Screen()

    @Serializable
    data class Details(val id: String): Screen()

    @Serializable
    data class Profile(val name: String? = null): Screen()

    @Serializable
    object FriendsList: Screen()
}