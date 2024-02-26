package ir.hossein.notekmm.domain.usecase

import ir.hossein.notekmm.domain.model.TvShow
import ir.hossein.notekmm.utilities.ApiResponse
import kotlinx.coroutines.flow.Flow

interface GetTvShowsUseCase {

    suspend operator fun invoke(page: Int): ApiResponse<Flow<List<TvShow>>>
}