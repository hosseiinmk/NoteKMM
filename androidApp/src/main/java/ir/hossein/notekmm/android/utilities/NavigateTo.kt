package ir.hossein.notekmm.android.utilities

import androidx.navigation.NavController

fun NavController.navigateTo(
    currentRoute: String?,
    route: String
) {
    when (route) {
        currentRoute -> {}
        else -> {
            navigate(route = route) {
                popUpTo(route = BottomBarItems.Notes.route) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        }
    }
}