package ir.hossein.notekmm.android.presentation.tvShow

import ir.hossein.notekmm.domain.model.TvShow

data class TvShowsUiState(
    val page: Int = 0,
    val tvShows: List<TvShow> = emptyList(),
    val isLoading: Boolean = false,
    val isLoadingMore: Boolean = false
)
