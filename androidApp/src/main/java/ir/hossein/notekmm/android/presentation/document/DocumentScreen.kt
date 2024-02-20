package ir.hossein.notekmm.android.presentation.document

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel

@Composable
fun DocumentScreen(viewModel: DocumentViewModel = koinViewModel()) {

    val state by viewModel.state

    Column(
        modifier = Modifier.fillMaxSize().padding(8.dp)
    ) {
        Text(text = state.document)
    }
}