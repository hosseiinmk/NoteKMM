package ir.hossein.notekmm.android.utilities

import androidx.navigation.NavController

fun NavController.navigateTo(route: String) {
    navigate(route = route) {
        popUpTo(route = BottomBarItems.Notes.route) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}