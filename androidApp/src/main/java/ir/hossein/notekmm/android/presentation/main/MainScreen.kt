package ir.hossein.notekmm.android.presentation.main

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ir.hossein.notekmm.android.presentation.addNote.AddNoteScreen
import ir.hossein.notekmm.android.presentation.note.NotesScreen
import ir.hossein.notekmm.android.presentation.tvShow.TvShowsScreen
import ir.hossein.notekmm.android.utilities.BottomBarItems
import ir.hossein.notekmm.android.utilities.Constant
import ir.hossein.notekmm.android.utilities.navigateTo

@Composable
fun MainScreen() {

    val navController = rememberNavController()

    Scaffold(bottomBar = {
        BottomBar(
            gotoNotes = { route -> navController.navigateTo(route = route) },
            gotoAddNote = { route -> navController.navigateTo(route = route) },
            gotoTvShows = { route -> navController.navigateTo(route = route) },
        )
    }) {
        NavHost(
            modifier = Modifier.padding(it),
            navController = navController,
            startDestination = Constant.NOTES_ROUTE
        ) {
            composable(route = Constant.NOTES_ROUTE) {
                NotesScreen()
            }
            composable(route = Constant.ADD_NOTE_ROUTE) {
                AddNoteScreen(
                    gotoNotes = {
                        navController.navigateTo(route = BottomBarItems.Notes.route)
                    }
                )
            }
            composable(route = Constant.TV_SHOWS) {
                TvShowsScreen(
                    onBack = {
                        navController.navigateTo(route = BottomBarItems.Notes.route)
                    }
                )
            }
        }
    }
}

@Composable
fun BottomBar(
    gotoNotes: (String) -> Unit,
    gotoAddNote: (String) -> Unit,
    gotoTvShows: (String) -> Unit
) {
    BottomAppBar(
        actions = {
            IconButton(onClick = { gotoNotes(BottomBarItems.Notes.route) }) {
                Icon(
                    imageVector = BottomBarItems.Notes.icon,
                    contentDescription = BottomBarItems.Notes.label
                )
            }
            IconButton(onClick = { gotoTvShows(BottomBarItems.TvShows.route) }) {
                Icon(
                    imageVector = BottomBarItems.TvShows.icon,
                    contentDescription = BottomBarItems.TvShows.label
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { gotoAddNote(BottomBarItems.AddNote.route) },
                modifier = Modifier.clip(RoundedCornerShape(50.dp)),
                containerColor = Color(0xFF0E86D4),
                contentColor = Color.White
            ) {
                Icon(
                    imageVector = BottomBarItems.AddNote.icon,
                    contentDescription = BottomBarItems.AddNote.label
                )
            }
        },
        containerColor = Color(0xFFf2f2f2)
    )
}