package alfianyabdullah.submission.core.data.network.response

import com.google.gson.annotations.SerializedName

data class GameResponse(
    @field:SerializedName("results")
    val results: List<GameItem>
)

data class GameItem(

    @field:SerializedName("background_image")
    val poster: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("rating")
    val rating: Double,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("genres")
    val genres: List<Genres>,

    @field:SerializedName("short_screenshots")
    val screenshots: List<Screenshots>
)

data class Genres(
    @field:SerializedName("name")
    val name: String
)

data class Screenshots(
    @field:SerializedName("image")
    val image: String
)
