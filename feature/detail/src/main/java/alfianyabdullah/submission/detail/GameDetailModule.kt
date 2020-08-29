package alfianyabdullah.submission.detail

import alfianyabdullah.submission.core.domain.usecase.LocalTaskInteractor
import alfianyabdullah.submission.core.domain.usecase.LocalTaskUseCase
import alfianyabdullah.submission.core.domain.usecase.NetworkTaskInteractor
import alfianyabdullah.submission.core.domain.usecase.NetworkTaskUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

@ExperimentalCoroutinesApi
val gamesDetailModule = module {
    single<NetworkTaskUseCase>(named("gamesdetailnetwork")) { NetworkTaskInteractor(get()) }
    single<LocalTaskUseCase>(named("gamesdetaillocal")) { LocalTaskInteractor(get()) }
    single { GameScreenshotAdapter(mutableListOf()) }

    viewModel { GameDetailViewModel(get(named("gamesdetailnetwork")), get(named("gamesdetaillocal"))) }
}