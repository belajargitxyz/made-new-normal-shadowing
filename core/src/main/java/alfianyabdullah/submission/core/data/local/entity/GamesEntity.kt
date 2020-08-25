package alfianyabdullah.submission.core.data.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_games")
data class GamesEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "game_id")
    val id: Int,

    @ColumnInfo(name = "game_poster")
    val poster: String,

    @ColumnInfo(name = "game_name")
    val name: String,

    @ColumnInfo(name = "game_rating")
    val rating: Double
)