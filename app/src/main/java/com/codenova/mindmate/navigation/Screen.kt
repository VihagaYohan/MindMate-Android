package com.codenova.mindmate.navigation

import kotlinx.serialization.Serializable

sealed class Screen {
    @Serializable
    object Login: Screen()
    object Register: Screen()
    object Welcome: Screen()

    // route for nested graph
    @Serializable
    object BottomNavGraph: Screen()

    // route inside nested graph
    @Serializable
    object Root: Screen()
    @Serializable
    data object Home: Screen()
    @Serializable
    object Journal: Screen()
    @Serializable
    object Mood: Screen()
    @Serializable
    data class Profile(val name: String? = null): Screen()

    @Serializable
    data class Details(val id: String): Screen()

    @Serializable
    object FriendsList: Screen()
}