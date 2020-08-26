package alfianyabdullah.submission.games

import alfianyabdullah.submission.base.GamesAdapter
import alfianyabdullah.submission.core.domain.usecase.GamesInteractor
import alfianyabdullah.submission.core.domain.usecase.GamesUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

@ExperimentalCoroutinesApi
val gamesModule = module {
    single<GamesUseCase> { GamesInteractor(get()) }
    single { GamesAdapter(mutableListOf()) }

    viewModel { GamesViewModel(get()) }
}