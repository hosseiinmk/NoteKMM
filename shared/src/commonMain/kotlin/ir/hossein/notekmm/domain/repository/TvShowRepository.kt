package ir.hossein.notekmm.domain.repository

import ir.hossein.notekmm.domain.model.TvShow
import ir.hossein.notekmm.utilities.ApiResponse
import kotlinx.coroutines.flow.Flow

interface TvShowRepository {

    suspend fun getTvShows(page: Int): ApiResponse<Flow<List<TvShow>>>
}