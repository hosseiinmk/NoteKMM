package ir.hossein.notekmm.android.presentation.tvShow

import androidx.lifecycle.viewModelScope
import ir.hossein.notekmm.android.core.BaseViewModel
import ir.hossein.notekmm.domain.usecase.GetTvShowsUseCase
import ir.hossein.notekmm.utilities.ApiResponse
import kotlinx.coroutines.launch

class TvShowsViewModel(
    private val getTvShowsUseCase: GetTvShowsUseCase
) : BaseViewModel<TvShowsUiState>(TvShowsUiState()) {

    init {
        getTvShows()
    }

    fun loadNextPage() {
        updateState { copy(page = stateValue().page + 1, loadingMore = true) }
        getTvShows()
    }

    private fun getTvShows() {
        showLoading()
        viewModelScope.launch {
            getTvShowsUseCase(stateValue().page).let { response ->
                when (response) {
                    is ApiResponse.OnSuccess -> {
                        response.data.collect { newTvShows ->
                            hideLoading()
                            updateState {
                                copy(tvShows = stateValue().tvShows + newTvShows)
                            }
                        }
                    }

                    is ApiResponse.OnFailure -> {
                        updateState { copy(page = stateValue().page - 1) }
                        hideLoading()
                        println(response.message)
                    }
                }
            }
        }
    }

    private fun showLoading() {
        when (stateValue().loadingMore) {
            true -> updateState { copy(loadingMore = true) }
            else -> updateState { copy(loading = true) }
        }
    }

    private fun hideLoading() {
        when (stateValue().loadingMore) {
            true -> updateState { copy(loadingMore = false) }
            else -> updateState { copy(loading = false) }
        }
    }
}

