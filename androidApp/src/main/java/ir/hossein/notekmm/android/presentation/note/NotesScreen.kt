package ir.hossein.notekmm.android.presentation.note

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ir.hossein.notekmm.domain.model.Note
import org.koin.androidx.compose.koinViewModel

@Composable
fun NotesScreen(
    viewModel: NotesViewModel = koinViewModel()
) {

    val state by viewModel.state

    if (state.notes.isEmpty()) EmptyScreen()
    else {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
        ) {
            items(state.notes) { note ->
                NotesItem(
                    note = note,
                    deleteNote = { viewModel.deleteNote(note = note) }
                )
            }
        }
    }
}

@Composable
fun NotesItem(
    note: Note,
    deleteNote: () -> Unit
) {
    Row(
        Modifier
            .fillMaxWidth()
            .background(color = Color.LightGray)
            .height(IntrinsicSize.Min)
            .padding(8.dp)
            .clip(shape = RoundedCornerShape(8.dp))
            .padding(8.dp)
    ) {
        Column() {
            Text(text = note.title)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = note.content)
        }
        IconButton(onClick = { deleteNote() }) {
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