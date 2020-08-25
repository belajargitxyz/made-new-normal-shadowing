package alfianyabdullah.submission.core.domain.usecase

import alfianyabdullah.submission.core.data.local.entity.GamesEntity
import alfianyabdullah.submission.core.domain.model.Game
import alfianyabdullah.submission.core.domain.repository.IGamesRepository

class GamesInteractor(private val iGamesRepository: IGamesRepository) : GamesUseCase {

    override suspend fun getAllGame() =
        iGamesRepository.getAllGame()

    override suspend fun findGameById(id: Int) =
        iGamesRepository.findGameById(id)

    override fun getAllGameInDatabase() =
        iGamesRepository.getAllGameInDatabase()

    override fun findGameInDatabaseById(id: Int) =
        iGamesRepository.findGameInDatabaseById(id)

    override suspend fun insertGameToDatabase(game: Game) =
        iGamesRepository.insertGameToDatabase(game)

    override suspend fun removeGameFromDatabase(game: Game) =
        iGamesRepository.removeGameFromDatabase(game)
}