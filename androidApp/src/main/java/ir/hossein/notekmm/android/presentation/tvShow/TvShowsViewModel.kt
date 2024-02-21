package ir.hossein.notekmm.android.presentation.tvShow

import androidx.compose.foundation.lazy.LazyListLayoutInfo
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ir.hossein.notekmm.domain.usecase.GetTvShowsUseCase
import kotlinx.coroutines.launch

class TvShowsViewModel(
    private val getTvShowsUseCase: GetTvShowsUseCase
) : ViewModel() {

    private val _state = mutableStateOf(TvShowsUiState())
    val state = _state

    init {
        getTvShows()
    }

    fun updateState(newState: TvShowsUiState) {
        _state.value = newState
    }

    fun loadMore() { getTvShows() }

    private fun getTvShows() {
        updateState(state.value.copy(isLoading = true))
        viewModelScope.launch {
            getTvShowsUseCase(_state.value.page).collect {
                updateState(state.value.copy(tvShows = it, isLoading = false))
            }
        }
    }
}