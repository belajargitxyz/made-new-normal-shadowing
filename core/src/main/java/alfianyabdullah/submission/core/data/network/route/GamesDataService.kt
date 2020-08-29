package alfianyabdullah.submission.core.data.network.route

import alfianyabdullah.submission.core.data.network.response.GameDetailResponse
import alfianyabdullah.submission.core.data.network.response.GameResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GamesDataService {

    @GET("api/games?page_size=40")
    suspend fun getAllGame(): Response<GameResponse?>

    @GET("api/games/{gameId}")
    suspend fun findGameById(@Path("gameId") gameId: Int): Response<GameDetailResponse?>
}