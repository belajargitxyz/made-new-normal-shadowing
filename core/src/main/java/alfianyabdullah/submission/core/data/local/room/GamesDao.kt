package alfianyabdullah.submission.core.data.local.room

import alfianyabdullah.submission.core.data.local.entity.GamesEntity
import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface GamesDao {
    @Query("SELECT * FROM tb_games")
    fun getAllGameInDatabase(): Flow<List<GamesEntity>>

    @Query("SELECT * FROM tb_games WHERE game_id LIKE :id")
    fun findGameInDatabaseById(id: Int): Flow<List<GamesEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGameToDatabase(entity: GamesEntity)

    @Delete
    suspend fun removeGameFromDatabase(entity: GamesEntity)
}