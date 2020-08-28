package alfianyabdullah.submission.core.data.local.room

import alfianyabdullah.submission.core.data.local.entity.GenreEntity
import androidx.room.TypeConverter
import com.google.gson.Gson

class GamesGenreConverter {
    companion object {

        @TypeConverter
        @JvmStatic
        fun fromGenreEntity(value: MutableList<GenreEntity>?): String {
            return Gson().toJson(value)
        }

        @TypeConverter
        @JvmStatic
        fun toGenreEntity(value: String): MutableList<GenreEntity>? {
            val objects =
                Gson().fromJson(value, Array<GenreEntity>::class.java) as Array<GenreEntity>
            return objects.toMutableList()
        }
    }
}