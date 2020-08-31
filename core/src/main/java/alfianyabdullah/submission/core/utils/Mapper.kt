package alfianyabdullah.submission.core.utils

import alfianyabdullah.submission.core.data.local.entity.GamesEntity
import alfianyabdullah.submission.core.data.local.entity.GenreEntity
import alfianyabdullah.submission.core.data.local.entity.ScreenshotEntity
import alfianyabdullah.submission.core.data.network.response.GameDetailResponse
import alfianyabdullah.submission.core.data.network.response.GameItem
import alfianyabdullah.submission.core.domain.model.*

class Mapper {

    fun <T> mappingListEntityToDomain(data: List<T>) = data.map {
        if (it is GamesEntity || it is GameItem) {
            try {
                val entity = it as GamesEntity
                Game(
                    id = entity.id,
                    poster = entity.poster,
                    name = entity.name,
                    rating = entity.rating,
                    genres = entity.genres.map { genre ->
                        GameGenre(genre.name)
                    },
                    screenshots = entity.screenshots.map { screenshot ->
                        GameScreenshot(screenshot.image)
                    }
                )
            } catch (e: Exception) {
                val item = it as GameItem
                Game(
                    id = item.id,
                    poster = item.poster,
                    name = item.name,
                    rating = item.rating,
                    genres = item.genres.map { genre ->
                        GameGenre(genre.name)
                    },
                    screenshots = item.screenshots.map { screenshot ->
                        GameScreenshot(screenshot.image)
                    }
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
        rating = game.rating,
        genres = game.genres.map { genre ->
            GenreEntity(genre.name)
        },
        screenshots = game.screenshots.map { screenshot ->
            ScreenshotEntity(screenshot.image)
        }
    )
}