package alfianyabdullah.submission.detail

import alfianyabdullah.submission.core.domain.usecase.NetworkTaskInteractor
import alfianyabdullah.submission.core.domain.usecase.NetworkTaskUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

@ExperimentalCoroutinesApi
val gamesDetailModule = module {
    single<NetworkTaskUseCase>(named("gamesdetail")) { NetworkTaskInteractor(get()) }

    viewModel { GameDetailViewModel(get(named("gamesdetail"))) }
}