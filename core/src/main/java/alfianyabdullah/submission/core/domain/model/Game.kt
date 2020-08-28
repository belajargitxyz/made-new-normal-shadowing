package alfianyabdullah.submission.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Game(
    val id: Int,
    val poster: String,
    val name: String,
    val rating: Double,
    val genres: List<GameGenre>,
    val screenshots: List<GameScreenshot>
) : Parcelable