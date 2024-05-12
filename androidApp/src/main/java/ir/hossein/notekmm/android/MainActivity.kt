package ir.hossein.notekmm.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ir.hossein.notekmm.android.utilities.Constant
import ir.hossein.notekmm.android.presentation.addNote.AddNoteScreen
import ir.hossein.notekmm.android.presentation.main.MainScreen
import ir.hossein.notekmm.android.presentation.note.NotesScreen
import ir.hossein.notekmm.android.presentation.theme.MyApplicationTheme
import ir.hossein.notekmm.android.presentation.tvShow.TvShowsScreen
import ir.hossein.notekmm.android.utilities.BottomBarItems
import ir.hossein.notekmm.android.utilities.navigateTo

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
//        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}