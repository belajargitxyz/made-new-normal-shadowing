package alfianyabdullah.submission.core.domain.usecase

import alfianyabdullah.submission.core.data.Resource
import alfianyabdullah.submission.core.domain.model.Game
import alfianyabdullah.submission.core.domain.model.GameDetail
import kotlinx.coroutines.flow.Flow

interface NetworkTaskUseCase {
    suspend fun getAllGame(): Flow<Resource<List<Game>>>
    suspend fun findGameById(id: Int): Flow<Resource<GameDetail>>
}