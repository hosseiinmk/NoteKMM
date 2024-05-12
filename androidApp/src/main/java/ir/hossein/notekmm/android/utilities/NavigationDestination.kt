package ir.hossein.notekmm.android.utilities

sealed class NavigationDestination(val route: String) {

    data object Notes: NavigationDestination(route = "notes")
    data object AddNote: NavigationDestination(route = "add_note")
    data object TvShows: NavigationDestination(route = "tv_shows")
}