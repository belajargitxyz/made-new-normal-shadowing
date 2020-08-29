package alfianyabdullah.submission.core.data.local.room

import alfianyabdullah.submission.core.data.local.entity.ScreenshotEntity
import androidx.room.TypeConverter
import com.google.gson.Gson

class GamesScreenshotConverter {
    companion object {

        @TypeConverter
        @JvmStatic
        fun fromScreenshotEntity(value: MutableList<ScreenshotEntity>?): String {
            return Gson().toJson(value)
        }

        @TypeConverter
        @JvmStatic
        fun toScreenshotEntity(value: String): MutableList<ScreenshotEntity>? {
            val objects =
                Gson().fromJson(
                    value,
                    Array<ScreenshotEntity>::class.java
                ) as Array<ScreenshotEntity>
            return objects.toMutableList()
        }
    }
}