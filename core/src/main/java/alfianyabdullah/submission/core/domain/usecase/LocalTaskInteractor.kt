package alfianyabdullah.submission.core.domain.usecase

import alfianyabdullah.submission.core.domain.model.Game
import alfianyabdullah.submission.core.domain.repository.IGamesRepository

class LocalTaskInteractor(private val iGamesRepository: IGamesRepository) : LocalTaskUseCase {
    override fun getAllGameInDatabase() =
        iGamesRepository.getAllGameInDatabase()

    override fun findGameInDatabaseById(id: Int) =
        iGamesRepository.findGameInDatabaseById(id)

    override suspend fun insertGameToDatabase(game: Game) =
        iGamesRepository.insertGameToDatabase(game)

    override suspend fun removeGameFromDatabase(game: Game) =
        iGamesRepository.removeGameFromDatabase(game)
}