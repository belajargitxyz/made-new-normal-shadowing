package alfianyabdullah.submission.core.domain.usecase

import alfianyabdullah.submission.core.domain.repository.IGamesRepository

class NetworkTaskInteractor(private val iGamesRepository: IGamesRepository) : NetworkTaskUseCase {
    override suspend fun getAllGame() =
        iGamesRepository.getAllGame()

    override suspend fun findGameById(id: Int) =
        iGamesRepository.findGameById(id)
}