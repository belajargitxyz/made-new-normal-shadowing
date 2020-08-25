package alfianyabdullah.submission.core.data.datasource

import alfianyabdullah.submission.core.data.local.entity.GamesEntity
import alfianyabdullah.submission.core.data.local.room.GamesDao

class GamesLocalDataSource(private val dao: GamesDao) {
    fun getAllGameInDatabase() = dao.getAllGameInDatabase()
    fun findGameInDatabaseById(id: Int) = dao.findGameInDatabaseById(id)
    suspend fun insertGameToDatabase(entity: GamesEntity) = dao.insertGameToDatabase(entity)
    suspend fun removeGameFromDatabase(entity: GamesEntity) = dao.removeGameFromDatabase(entity)
}