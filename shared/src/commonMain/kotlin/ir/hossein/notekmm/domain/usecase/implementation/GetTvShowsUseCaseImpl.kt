package ir.hossein.notekmm.domain.usecase.implementation

import ir.hossein.notekmm.domain.model.TvShow
import ir.hossein.notekmm.domain.repository.TvShowRepository
import ir.hossein.notekmm.domain.usecase.GetTvShowsUseCase
import kotlinx.coroutines.flow.Flow

class GetTvShowsUseCaseImpl(
    private val repository: TvShowRepository
) : GetTvShowsUseCase {

    override suspend fun invoke(page: Int): Flow<List<TvShow>> = repository.getTvShows(page = page)
}