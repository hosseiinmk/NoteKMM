package ir.hossein.notekmm.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ir.hossein.notekmm.android.utilities.Constant
import ir.hossein.notekmm.android.presentation.addNote.AddNoteScreen
import ir.hossein.notekmm.android.presentation.note.NotesScreen
import ir.hossein.notekmm.android.presentation.theme.MyApplicationTheme
import ir.hossein.notekmm.android.utilities.BottomBarItems

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentDestination = navBackStackEntry?.destination?.route

                    Scaffold(bottomBar = {
                        BottomBar(
                            gotoNotes = { route ->
                                if (route != currentDestination) navController.popBackStack()
                            },
                            gotoAddNote = { route ->
                                if (route != currentDestination) navController.navigate(route = route)
                            }
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
                                    gotoNotes = { navController.popBackStack() }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun BottomBar(
    gotoNotes: (String) -> Unit,
    gotoAddNote: (String) -> Unit
) {
    BottomAppBar(
        actions = {
            IconButton(onClick = { gotoNotes(BottomBarItems.Notes.route) }) {
                Icon(
                    imageVector = BottomBarItems.Notes.icon,
                    contentDescription = BottomBarItems.Notes.label
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { gotoAddNote(BottomBarItems.AddNote.route) }) {
                Icon(
                    imageVector = BottomBarItems.AddNote.icon,
                    contentDescription = BottomBarItems.AddNote.label
                )
            }
        }
    )
}