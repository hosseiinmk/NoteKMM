package ir.hossein.notekmm.android.presentation.tvShow

import androidx.lifecycle.viewModelScope
import ir.hossein.notekmm.android.core.BaseViewModel
import ir.hossein.notekmm.android.utilities.generateColorList
import ir.hossein.notekmm.android.utilities.log
import ir.hossein.notekmm.domain.usecase.GetTvShowsUseCase
import ir.hossein.notekmm.utilities.ApiResponse
import kotlinx.coroutines.launch

class TvShowsViewModel(
    private val getTvShowsUseCase: GetTvShowsUseCase
) : BaseViewModel<TvShowsUiState>(TvShowsUiState()) {

    init {
        getTvShows()
    }

    private fun getTvShows() {
        showLoading()
        viewModelScope.launch {
            getTvShowsUseCase(stateValue().page).let { response ->
                when (response) {
                    is ApiResponse.OnSuccess -> {
                        response.data.collect { newTvShows ->
                            updateState {
                                copy(tvShows = stateValue().tvShows + newTvShows)
                            }
                            setTheme(stateValue().tvShows.size)
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

    fun setTheme(size: Int) {
        generateColorList(size = size).let { colors ->
            updateState {
                copy(backgroundColor = colors)
            }
            hideLoading()
        }
    }

    fun loadNextPage() {
        updateState { copy(page = stateValue().page + 1, loadingMore = true) }
        getTvShows()
    }

    private fun showLoading() {
        if (!stateValue().loadingMore) updateState { copy(loading = true) }
    }

    private fun hideLoading() {
        when (stateValue().loadingMore) {
            true -> updateState { copy(loadingMore = false) }
            else -> updateState { copy(loading = false) }
        }
    }
}

