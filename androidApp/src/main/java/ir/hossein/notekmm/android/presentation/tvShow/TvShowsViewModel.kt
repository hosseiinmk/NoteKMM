package ir.hossein.notekmm.android.presentation.tvShow

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ir.hossein.notekmm.domain.usecase.GetTvShowsUseCase
import ir.hossein.notekmm.utilities.ApiResponse
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
        nextPage()
        getTvShows(true)
    }

    private fun getTvShows(loadingMore: Boolean) {
        enableLoading(loadingMore)
        viewModelScope.launch {
            getTvShowsUseCase(_state.value.page).let { response ->
                when(response) {
                    is ApiResponse.OnSuccess -> {
                        response.data.collect { newTvShows ->
                            updateState(getState().copy(tvShows = getState().tvShows + newTvShows))
                            disableLoading(loadingMore)
                        }
                    }
                    is ApiResponse.OnFailure -> {
                        previousPage()
                        disableLoading(true)
                        println(response.message)
                    }
                }
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

    private fun nextPage() {
        updateState(getState().copy(page = getState().page + 1))
    }

    private fun previousPage() {
        updateState(getState().copy(page = getState().page - 1))
    }
}