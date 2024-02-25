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
        getTvShows(false)
    }

    private fun getState() = state.value

    private fun updateState(newState: TvShowsUiState) {
        _state.value = newState
    }

    fun loadMore() {
        updateState(getState().copy(page = getState().page + 1))
        getTvShows(true)
    }

    private fun getTvShows(loadingMore: Boolean) {
        enableLoading(loadingMore)
        viewModelScope.launch {
            getTvShowsUseCase(_state.value.page).collect {
                updateState(getState().copy(tvShows = it))
                disableLoading(loadingMore)
            }
        }
    }

    private fun enableLoading(loadingMore: Boolean) {
        when(loadingMore) {
            false -> updateState(getState().copy(isLoading = true))
            true -> updateState(getState().copy(isLoadingMore = true))
        }
    }

    private fun disableLoading(loadingMore: Boolean) {
        when(loadingMore) {
            false -> updateState(getState().copy(isLoading = false))
            true -> updateState(getState().copy(isLoadingMore = false))
        }
    }
}