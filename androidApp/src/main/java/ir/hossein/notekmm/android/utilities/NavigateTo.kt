package ir.hossein.notekmm.android.utilities

import androidx.navigation.NavController

fun NavController.navigateTo(route: String) {
    navigate(route = route) {
        popUpTo(route = NavigationDestination.Notes.route) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}