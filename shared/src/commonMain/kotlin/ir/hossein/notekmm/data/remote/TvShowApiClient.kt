package ir.hossein.notekmm.data.remote

import ir.hossein.notekmm.data.model.TvShow

interface TvShowApiClient {

    suspend fun getTvShows(page: Int): List<TvShow>
}