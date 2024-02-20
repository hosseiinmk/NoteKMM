package ir.hossein.notekmm.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText

class KtorApiClient {

    private val client = HttpClient()

    suspend fun getDoc(): String {
        val response = client.get("https://ktor.io/docs/")
        return response.bodyAsText()
    }
}