package ir.hossein.notekmm.android.presentation.note

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import ir.hossein.notekmm.android.presentation.tvShow.ShowLoading
import ir.hossein.notekmm.domain.model.Note
import org.koin.androidx.compose.koinViewModel

@Composable
fun NotesScreen(
    viewModel: NotesViewModel = koinViewModel()
) {

    val state by viewModel.state().collectAsState()

    AnimatedContent(
        targetState = state.loading,
        label = "Animated Content"
    ) { loading ->
        when (loading) {
            false -> {
                AnimatedContent(targetState = state.empty, label = "") {isEmpty ->
                    when (isEmpty) {
                        false -> NotesList(
                            state = state,
                            deleteNote = { note -> viewModel.deleteNote(note = note) }
                        )
                        else -> EmptyScreen()
                    }
                }
            }
            true -> ShowLoading()
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NotesList(
    state: NotesUiState,
    deleteNote: (Note) -> Unit
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        itemsIndexed(state.notes) { position, note ->
            NotesItem(
                modifier = Modifier
                    .animateItemPlacement()
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clip(shape = RoundedCornerShape(8.dp))
                    .background(color = state.backgroundColor[position])
                    .padding(8.dp),
                note = note,
            ) { deleteNote(note) }
        }
    }
}

@Composable
fun NotesItem(
    modifier: Modifier,
    note: Note,
    deleteNote: () -> Unit
) {
    ConstraintLayout(modifier = modifier) {

        val (column, deleteBtn) = createRefs()

        Column(Modifier.constrainAs(column) {
            start.linkTo(parent.start)
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
            height = Dimension.fillToConstraints
        }) {
            Text(text = note.title)
            Text(text = note.content)
        }
        IconButton(onClick = { deleteNote() }, modifier = Modifier.constrainAs(deleteBtn) {
            end.linkTo(parent.end)
            top.linkTo(parent.top)
        }) {
            Icon(imageVector = Icons.Default.Delete, contentDescription = null)
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