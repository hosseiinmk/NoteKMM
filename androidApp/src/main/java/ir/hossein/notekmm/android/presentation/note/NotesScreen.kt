package ir.hossein.notekmm.android.presentation.note

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import ir.hossein.notekmm.android.presentation.loading.LoadingScreen
import ir.hossein.notekmm.domain.model.Note
import org.koin.androidx.compose.koinViewModel

@Composable
fun NotesScreen(
    viewModel: NotesViewModel = koinViewModel(),
) {

    val state by viewModel.state().collectAsState()

    AnimatedContent(
        targetState = state.loading,
        label = "Animated Content"
    ) { loading ->
        when (loading) {
            false -> {
                AnimatedContent(targetState = state.empty, label = "") { isEmpty ->
                    when (isEmpty) {
                        false -> {
                            NotesList(state = state) { note, position ->
                                viewModel.deleteNote(
                                    note = note,
                                    position = position
                                )
                            }
                        }

                        else -> EmptyScreen()
                    }
                }
            }

            else -> LoadingScreen()
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NotesList(
    state: NotesUiState,
    deleteNote: (Note, Int) -> Unit
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        itemsIndexed(items = state.notes, key = { _, item -> item.id }) { position, note ->
            NotesItem(
                modifier = Modifier
                    .animateItemPlacement()
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clip(shape = RoundedCornerShape(8.dp))
                    .background(color = state.backgroundColor[position])
                    .padding(8.dp),
                note = note,
            ) { deleteNote(note, position) }
        }
    }
}

@Composable
fun NotesItem(
    modifier: Modifier,
    note: Note,
    deleteNote: () -> Unit
) {
    Row(modifier = modifier) {
        Column(modifier = Modifier.weight(1f)) {
            Text(text = note.title)
            Text(text = note.content)
        }
        Box {
            IconButton(onClick = { deleteNote() }) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = null)
            }
        }
    }
}

@Composable
fun EmptyScreen() {
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "There is not any note in list")
    }
}