package alfianyabdullah.submission.games

import alfianyabdullah.submission.base.GamesAdapter
import alfianyabdullah.submission.core.domain.usecase.NetworkTaskInteractor
import alfianyabdullah.submission.core.domain.usecase.NetworkTaskUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val GAME_QUALIFIER = "game"

@ExperimentalCoroutinesApi
val gamesModule = module {
    single<NetworkTaskUseCase>(named(GAME_QUALIFIER)) { NetworkTaskInteractor(get()) }
    //factory(named(GAME_QUALIFIER)) { GamesAdapter(mutableListOf()) }

    viewModel { GamesViewModel(get(named(GAME_QUALIFIER))) }
}