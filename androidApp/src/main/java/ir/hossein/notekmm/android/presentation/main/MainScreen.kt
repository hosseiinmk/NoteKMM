package ir.hossein.notekmm.android.presentation.main

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
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

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                goToDestination = { destination ->
                    navController.navigateTo(route = destination)
                }
            )
        }
    ) {
        NavHost(
            modifier = Modifier.padding(it),
            navController = navController,
            startDestination = Constant.NOTES_ROUTE
        ) {
            composable(route = Constant.NOTES_ROUTE) {
                NotesScreen()
            }
            composable(
                route = Constant.ADD_NOTE_ROUTE,
                enterTransition = {
                    fadeIn(
                        animationSpec = tween(300, easing = LinearEasing)
                    ) + slideIntoContainer(
                        animationSpec = tween(300, easing = EaseIn),
                        towards = AnimatedContentTransitionScope.SlideDirection.Up
                    )
                },
            ) {
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
fun BottomNavigationBar(
    goToDestination: (String) -> Unit
) {

    val items = listOf(
        BottomBarItems.Notes,
        BottomBarItems.TvShows,
        BottomBarItems.AddNote
    )

    val selectedItem = remember { mutableIntStateOf(0) }

    NavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedItem.intValue == index,
                onClick = {
                    goToDestination(item.route)
                    selectedItem.intValue = index
                },
                icon = {
                    Icon(imageVector = item.icon, contentDescription = null)
                },
                label = {
                    Text(text = item.label)
                }
            )
        }
    }
}