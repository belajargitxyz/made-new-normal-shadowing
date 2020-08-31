package alfianyabdullah.submission.detail

import alfianyabdullah.submission.core.domain.usecase.LocalTaskInteractor
import alfianyabdullah.submission.core.domain.usecase.LocalTaskUseCase
import alfianyabdullah.submission.core.domain.usecase.NetworkTaskInteractor
import alfianyabdullah.submission.core.domain.usecase.NetworkTaskUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val GAME_DETAIL_QUALIFIER_LOCAL = "games_detail_local"
const val GAME_DETAIL_QUALIFIER_NETWORK = "games_detail_network"

@ExperimentalCoroutinesApi
val gamesDetailModule = module {
    single<NetworkTaskUseCase>(named(GAME_DETAIL_QUALIFIER_NETWORK)) { NetworkTaskInteractor(get()) }
    single<LocalTaskUseCase>(named(GAME_DETAIL_QUALIFIER_LOCAL)) { LocalTaskInteractor(get()) }
   // single { GameScreenshotAdapter(mutableListOf()) }

    viewModel { GameDetailViewModel(get(named(GAME_DETAIL_QUALIFIER_NETWORK)), get(named(GAME_DETAIL_QUALIFIER_LOCAL))) }
}