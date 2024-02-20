package ir.hossein.notekmm.android.presentation.addNote

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ir.hossein.notekmm.domain.model.Note
import org.koin.androidx.compose.koinViewModel

@Composable
fun AddNoteScreen(
    viewModel: AddNoteViewModel = koinViewModel(),
    gotoNotes: () -> Unit
) {

    val state by viewModel.state

    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = state.title,
            onValueChange = { viewModel.updateState(state.copy(title = it)) },
            placeholder = { Text(text = "title") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = state.content,
            onValueChange = { viewModel.updateState(state.copy(content = it)) },
            placeholder = { Text(text = "Content") }
        )
        Button(onClick = {
            viewModel.addNote(Note(title = state.title, content = state.content))
            gotoNotes()
        }){
            Text(text = "Add Note")
        }
    }
}