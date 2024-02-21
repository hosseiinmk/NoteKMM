package ir.hossein.notekmm.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import ir.hossein.notekmm.data.model.TvShow
import ir.hossein.notekmm.data.model.TvShowResponse
import ir.hossein.notekmm.utilities.Constant.BASE_URL

class TvShowApiClientImpl(private val httpClient: HttpClient) : TvShowApiClient {

    override suspend fun getTvShows(page: Int): List<TvShow> =
        httpClient.get("${BASE_URL}/most-popular") {
            url {
                parameters.append("page", "$page")
            }
        }.body<TvShowResponse>().tvShows
}