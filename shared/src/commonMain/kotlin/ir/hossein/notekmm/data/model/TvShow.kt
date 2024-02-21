package ir.hossein.notekmm.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TvShowResponse(
    val page: Int,
    val pages: Int,
    @SerialName("tv_shows")
    val tvShows: List<TvShow>
)
@Serializable
data class TvShow(
    val id: Int,
    val name: String,
    @SerialName("start_date")
    val startDate: String,
    val status: String,
    @SerialName("image_thumbnail_path")
    val thumbnail: String,
)
