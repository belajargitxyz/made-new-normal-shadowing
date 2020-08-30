package alfianyabdullah.submission.core.domain.usecase

import alfianyabdullah.submission.core.domain.model.Game
import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow

interface LocalTaskUseCase {
    fun getAllGameInDatabase(): Flow<List<Game>>
    fun findGameInDatabaseById(id: Int): Flow<List<Game>>
    suspend fun insertGameToDatabase(game: Game)
    suspend fun removeGameFromDatabase(game: Game)
}