package ir.hossein.notekmm.android.utilities

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarItems(val route: String, val icon: ImageVector, val label: String) {
    data object Notes : BottomBarItems("notes", Icons.Default.Home, "notes")
    data object AddNote : BottomBarItems("add_note", Icons.Default.Add, "Add Note")
    data object Document : BottomBarItems("document", Icons.Default.Info, "Document")
}