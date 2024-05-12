package ir.hossein.notekmm.android.utilities

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarItems(val route: String, val icon: ImageVector, val label: String) {

    data object Notes : BottomBarItems(
        route = NavigationDestination.Notes.route,
        icon = Icons.Default.Home,
        label = "notes"
    )

    data object AddNote : BottomBarItems(
        route = NavigationDestination.AddNote.route,
        icon = Icons.Default.Add,
        label = "Add Note"
    )

    data object TvShows : BottomBarItems(
        route = NavigationDestination.TvShows.route,
        icon = Icons.Default.PlayArrow,
        label = "Tv Shows"
    )
}