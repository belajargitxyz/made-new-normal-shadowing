package alfianyabdullah.submission.core.data.local.room

import alfianyabdullah.submission.core.data.local.entity.GamesEntity
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [GamesEntity::class], version = 1, exportSchema = false)
abstract class GamesDatabase: RoomDatabase() {
    abstract fun gamesDao(): GamesDao
}