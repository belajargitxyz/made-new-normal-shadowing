package alfianyabdullah.submission.core.utils

import alfianyabdullah.submission.core.data.local.entity.GamesEntity
import alfianyabdullah.submission.core.data.network.response.GameDetailResponse
import alfianyabdullah.submission.core.data.network.response.GameItem
import alfianyabdullah.submission.core.domain.model.Game
import alfianyabdullah.submission.core.domain.model.GameDetail
import alfianyabdullah.submission.core.domain.model.GamePublisher
import java.lang.IllegalArgumentException

class Mapper {

    fun <T> mappingListEntityToDomain(data: List<T>) = data.map {
        if (it is GamesEntity || it is GameItem) {
            try {
                val entity = it as GamesEntity
                Game(
                    id = entity.id,
                    poster = entity.poster,
                    name = entity.name,
                    rating = entity.rating
                )
            } catch (e: Exception) {
                val item = it as GameItem
                Game(
                    id = item.id,
                    poster = item.poster,
                    name = item.name,
                    rating = item.rating
                )
            }
        } else throw IllegalArgumentException("Undefined type")

    }

    fun mappingDetailEntityToDomain(data: GameDetailResponse) =
        GameDetail(
            desc = data.descriptionRaw,
            publishers = data.publishers.map {
                GamePublisher(it.name)
            }
        )

    fun mappingDomainToEntity(game: Game) = GamesEntity(
        id = game.id,
        poster = game.poster,
        name = game.name,
        rating = game.rating
    )
}