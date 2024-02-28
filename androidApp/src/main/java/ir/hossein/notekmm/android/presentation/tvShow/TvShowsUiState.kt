package ir.hossein.notekmm.android.presentation.tvShow

import ir.hossein.notekmm.domain.model.TvShow

data class TvShowsUiState(
    val page: Int = 1,
    val tvShows: List<TvShow> = emptyList(),
    val loading: Boolean = false,
    val loadingMore: Boolean = false
)
