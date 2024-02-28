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

    init { getTvShows() }

    private fun getState() = state.value

    private fun updateState(newState: TvShowsUiState) { _state.value = newState }

    fun loadNextPage() {
        updateState(getState().copy(page = getState().page + 1, loadingMore = true))
        getTvShows()
    }

    private fun getTvShows() {
        showLoading()
        viewModelScope.launch {
            getTvShowsUseCase(_state.value.page).let { response ->
                when (response) {
                    is ApiResponse.OnSuccess -> {
                        response.data.collect { newTvShows ->
                            updateState(getState().copy(tvShows = getState().tvShows + newTvShows))
                            hideLoading()
                        }
                    }

                    is ApiResponse.OnFailure -> {
                        updateState(getState().copy(page = getState().page - 1))
                        hideLoading()
                        println(response.message)
                    }
                }
            }
        }
    }

    private fun showLoading() {
        when (getState().loadingMore) {
            true -> updateState(getState().copy(loadingMore = true))
            else -> updateState(getState().copy(loading = true))
        }
    }

    private fun hideLoading() {
        when (getState().loadingMore) {
            true -> updateState(getState().copy(loadingMore = false))
            else -> updateState(getState().copy(loading = false))
        }
    }
}