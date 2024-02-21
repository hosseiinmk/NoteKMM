package ir.hossein.notekmm.domain.repository

import ir.hossein.notekmm.domain.model.TvShow
import kotlinx.coroutines.flow.Flow

interface TvShowRepository {

    suspend fun getTvShows(page: Int): Flow<List<TvShow>>
}