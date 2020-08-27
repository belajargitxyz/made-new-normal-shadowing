package alfianyabdullah.submission.games

import alfianyabdullah.submission.base.GamesAdapter
import alfianyabdullah.submission.core.domain.usecase.NetworkTaskInteractor
import alfianyabdullah.submission.core.domain.usecase.NetworkTaskUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

@ExperimentalCoroutinesApi
val gamesModule = module {
    single<NetworkTaskUseCase> { NetworkTaskInteractor(get()) }
    single { GamesAdapter(mutableListOf()) }

    viewModel { GamesViewModel(get()) }
}