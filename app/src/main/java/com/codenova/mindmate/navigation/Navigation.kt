package com.codenova.mindmate.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.codenova.mindmate.ui.view.RootPage
import com.codenova.mindmate.ui.view.home.HomePage
import com.codenova.mindmate.ui.view.journal.JournalPage
import com.codenova.mindmate.ui.view.login.LoginPage
import com.codenova.mindmate.ui.view.mood.MoodPage
import com.codenova.mindmate.ui.view.profile.ProfilePage

@Composable
fun AppNavigation(navController: NavHostController, startDestination: Screen) {

    // implementing NavHost
    NavHost(navController = navController, startDestination = startDestination) {
        composable<Screen.Login> {
            LoginPage(onNavigateToBottomNavGraph = {
                navController.navigate(route = Screen.BottomNavGraph)
            })
        }
        composable<Screen.Home> {
            HomePage(onNavigateToProfile = {
                navController.navigate(Screen.Profile(name = "Jane Doe"))
            })
        }
        composable<Screen.Profile> { backStackEntry ->
            // val name = backStackEntry.arguments?.getString("name")
            val profile: Screen.Profile = backStackEntry.toRoute()
            ProfilePage(
                name = profile.name,
                onNavigateToHome = {
                    navController.navigate(Screen.Home)
                })
        }

        // bottom navigation nested graph
        navigation<Screen.BottomNavGraph>(startDestination = Screen.Root) {
            composable<Screen.Root> {
                RootPage()
            }
            composable<Screen.Home> {
                HomePage(onNavigateToProfile = {
                    navController.navigate(Screen.Profile(name = "Jane Doe"))
                })
            }
            composable<Screen.Journal> {
                JournalPage()
            }
            composable<Screen.Mood> { MoodPage() }
            composable<Screen.Profile> { backStackEntry ->
                val profile: Screen.Profile = backStackEntry.toRoute()
                ProfilePage(
                    name = profile.name,
                    onNavigateToHome = {
                        navController.navigate(Screen.Login)
                    })
            }
        }
    }
}