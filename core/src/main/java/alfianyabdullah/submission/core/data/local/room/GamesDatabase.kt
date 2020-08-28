package alfianyabdullah.submission.core.data.local.room

import alfianyabdullah.submission.core.data.local.entity.GamesEntity
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters

@Database(entities = [GamesEntity::class], version = 1, exportSchema = false)
@TypeConverters(GamesGenreConverter::class, GamesScreenshotConverter::class)
abstract class GamesDatabase : RoomDatabase() {
    abstract fun gamesDao(): GamesDao
}