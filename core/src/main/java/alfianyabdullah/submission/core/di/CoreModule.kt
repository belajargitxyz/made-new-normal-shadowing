package alfianyabdullah.submission.core.di

import alfianyabdullah.submission.core.data.datasource.GamesLocalDataSource
import alfianyabdullah.submission.core.data.datasource.GamesNetworkDataSource
import alfianyabdullah.submission.core.data.local.room.GamesDatabase
import alfianyabdullah.submission.core.data.network.route.GamesDataClient
import alfianyabdullah.submission.core.data.repository.GamesRepository
import alfianyabdullah.submission.core.domain.repository.IGamesRepository
import alfianyabdullah.submission.core.utils.Mapper
import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            GamesDatabase::class.java,
            "db_games"
        ).build()
    }

    factory { get<GamesDatabase>().gamesDao() }
}

val networkModule = module {
    single {
        GamesDataClient().service
    }
}

val repositoryModule = module {
    single { GamesLocalDataSource(get()) }
    single { GamesNetworkDataSource(get()) }
    single { Mapper() }

    single<IGamesRepository> {
        GamesRepository(
            get(), get(), get()
        )
    }
}

