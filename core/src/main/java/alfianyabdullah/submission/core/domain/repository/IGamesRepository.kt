package alfianyabdullah.submission.core.domain.repository

import alfianyabdullah.submission.core.data.Resource
import alfianyabdullah.submission.core.domain.model.Game
import alfianyabdullah.submission.core.domain.model.GameDetail
import kotlinx.coroutines.flow.Flow

interface IGamesRepository {
    suspend fun getAllGame(): Flow<Resource<List<Game>>>
    suspend fun findGameById(id: Int): Flow<Resource<GameDetail>>

    fun getAllGameInDatabase(): Flow<List<Game>>
    fun findGameInDatabaseById(id: Int): Flow<List<Game>>
    suspend fun insertGameToDatabase(game: Game)
    suspend fun removeGameFromDatabase(game: Game)
}