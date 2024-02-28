package ir.hossein.notekmm.android.presentation.tvShow

import androidx.compose.ui.graphics.Color
import ir.hossein.notekmm.domain.model.TvShow

data class TvShowsUiState(
    val page: Int = 1,
    val tvShows: List<TvShow> = emptyList(),
    val loading: Boolean = false,
    val loadingMore: Boolean = false,
    val backgroundColor: List<Color> = emptyList()
)
