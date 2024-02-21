package ir.hossein.notekmm.domain.usecase

import ir.hossein.notekmm.domain.model.TvShow
import kotlinx.coroutines.flow.Flow

interface GetTvShowsUseCase {

    suspend operator fun invoke(page: Int): Flow<List<TvShow>>
}