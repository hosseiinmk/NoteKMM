package ir.hossein.notekmm.domain.usecase.implementation

import ir.hossein.notekmm.domain.model.TvShow
import ir.hossein.notekmm.domain.repository.TvShowRepository
import ir.hossein.notekmm.domain.usecase.GetTvShowsUseCase
import ir.hossein.notekmm.utilities.ApiResponse
import kotlinx.coroutines.flow.Flow

class GetTvShowsUseCaseImpl(
    private val repository: TvShowRepository
) : GetTvShowsUseCase {

    override suspend fun invoke(page: Int): ApiResponse<Flow<List<TvShow>>> =
        repository.getTvShows(page = page)
}