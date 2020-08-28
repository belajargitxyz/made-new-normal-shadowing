package alfianyabdullah.submission.core.data.datasource

import alfianyabdullah.submission.core.data.network.response.GameDetailResponse
import alfianyabdullah.submission.core.data.network.response.GameItem
import alfianyabdullah.submission.core.data.network.route.GamesDataResponse
import alfianyabdullah.submission.core.data.network.route.GamesDataService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception

class GamesNetworkDataSource(private val gamesDataService: GamesDataService) {

    suspend fun getAllGame(): Flow<GamesDataResponse<List<GameItem>>> = flow {
        try {
            val response = gamesDataService.getAllGame()
            if (response.isSuccessful) {
                val result = response.body()?.results
                if (result?.isNotEmpty() == true) {
                    emit(GamesDataResponse.Success(result))
                } else {
                    emit(GamesDataResponse.Empty)
                }

            } else {
                emit(GamesDataResponse.Error("Something happen with data!"))
            }
        } catch (e: Exception) {
            emit(GamesDataResponse.Error("Something happen with network!"))
        }

    }.flowOn(Dispatchers.IO)

    suspend fun findGameById(id: Int): Flow<GamesDataResponse<GameDetailResponse>> = flow {
        try {
            val response = gamesDataService.findGameById(id)
            if (response.isSuccessful) {
                val result = response.body()
                if (result != null) {
                    emit(GamesDataResponse.Success(result))
                } else {
                    emit(GamesDataResponse.Empty)
                }

            } else {
                emit(GamesDataResponse.Error("Something happen with data!"))
            }
        } catch (e: Exception) {
            emit(GamesDataResponse.Error("Something happen with network!"))
        }

    }.flowOn(Dispatchers.IO)
}