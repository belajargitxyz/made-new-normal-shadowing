package alfianyabdullah.submission.favorite

import alfianyabdullah.submission.base.GamesAdapter
import alfianyabdullah.submission.core.domain.usecase.LocalTaskInteractor
import alfianyabdullah.submission.core.domain.usecase.LocalTaskUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

@ExperimentalCoroutinesApi
val gameFavoritesModule = module {
    single<LocalTaskUseCase>(named("gamesfavoritelocal")) { LocalTaskInteractor(get()) }
    single(named("gamesfavoritelocal")) { GamesAdapter(mutableListOf()) }

    viewModel {
        GameFavoritesViewModel(
            get(named("gamesfavoritelocal"))
        )
    }
}