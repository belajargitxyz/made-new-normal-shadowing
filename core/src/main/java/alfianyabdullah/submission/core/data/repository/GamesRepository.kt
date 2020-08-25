package alfianyabdullah.submission.core.data.repository

import alfianyabdullah.submission.core.data.Resource
import alfianyabdullah.submission.core.data.datasource.GamesLocalDataSource
import alfianyabdullah.submission.core.data.datasource.GamesNetworkDataSource
import alfianyabdullah.submission.core.data.network.route.GamesDataResponse
import alfianyabdullah.submission.core.domain.model.Game
import alfianyabdullah.submission.core.domain.model.GameDetail
import alfianyabdullah.submission.core.domain.repository.IGamesRepository
import alfianyabdullah.submission.core.utils.Mapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class GamesRepository(
    private val localDataSource: GamesLocalDataSource,
    private val networkDataSource: GamesNetworkDataSource,
    private val mapper: Mapper
) : IGamesRepository {
    override suspend fun getAllGame(): Flow<Resource<List<Game>>> {
        return flow {
            emit(Resource.Loading())
            when (val result = networkDataSource.getAllGame().first()) {
                is GamesDataResponse.Success -> {
                    emit(Resource.Success(mapper.mappingListEntityToDomain(result.data)))
                }

                is GamesDataResponse.Empty -> {
                    emit(Resource.Success(listOf()))
                }

                is GamesDataResponse.Error -> {
                    emit(Resource.Error(result.errorMessage))
                }
            }
        }
    }

    override suspend fun findGameById(id: Int): Flow<Resource<GameDetail>> {
        return flow {
            emit(Resource.Loading())
            when (val result = networkDataSource.findGameById(id).first()) {
                is GamesDataResponse.Success -> {
                    emit(Resource.Success(mapper.mappingDetailEntityToDomain(result.data)))
                }

                is GamesDataResponse.Empty -> {
                    emit(Resource.Error("Empty data"))
                }

                is GamesDataResponse.Error -> {
                    emit(Resource.Error(result.errorMessage))
                }
            }
        }
    }

    override fun getAllGameInDatabase(): Flow<List<Game>> {
        return flow {
            val data = localDataSource.getAllGameInDatabase().first()
            emit(mapper.mappingListEntityToDomain(data))
        }
    }

    override fun findGameInDatabaseById(id: Int): Flow<List<Game>> {
        return flow {
            val data = localDataSource.findGameInDatabaseById(id).first()
            emit(mapper.mappingListEntityToDomain(data))
        }
    }

    override suspend fun insertGameToDatabase(game: Game) =
        localDataSource.insertGameToDatabase(mapper.mappingDomainToEntity(game))

    override suspend fun removeGameFromDatabase(game: Game) =
        localDataSource.removeGameFromDatabase(mapper.mappingDomainToEntity(game))

}