package ir.hossein.notekmm.data.repository

import ir.hossein.notekmm.data.remote.TvShowApiClientImpl
import ir.hossein.notekmm.domain.model.TvShow
import ir.hossein.notekmm.domain.repository.TvShowRepository
import ir.hossein.notekmm.utilities.ApiResponse
import ir.hossein.notekmm.utilities.TvShowMapper
import kotlinx.coroutines.flow.Flow

class TvShowRepositoryImpl(
    private val api: TvShowApiClientImpl,
    private val tvShowMapper: TvShowMapper
) : TvShowRepository {

    override suspend fun getTvShows(page: Int): ApiResponse<Flow<List<TvShow>>> =
        try {
            ApiResponse.OnSuccess(data = tvShowMapper.map(api.getTvShows(page = page)))
        } catch (e: Exception) {
            ApiResponse.OnFailure(message = e.stackTraceToString())
        }
}