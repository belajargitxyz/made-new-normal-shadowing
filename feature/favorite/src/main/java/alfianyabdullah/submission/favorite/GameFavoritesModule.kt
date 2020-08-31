package alfianyabdullah.submission.favorite

import alfianyabdullah.submission.core.domain.usecase.LocalTaskInteractor
import alfianyabdullah.submission.core.domain.usecase.LocalTaskUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val GAME_FAVORITE_QUALIFIER = "game_favorite"

@ExperimentalCoroutinesApi
val gameFavoritesModule = module {
    single<LocalTaskUseCase>(named(GAME_FAVORITE_QUALIFIER)) { LocalTaskInteractor(get()) }
//    single(named(GAME_FAVORITE_QUALIFIER)) { GamesAdapter(mutableListOf()) }

    viewModel {
        GameFavoritesViewModel(
            get(named(GAME_FAVORITE_QUALIFIER))
        )
    }
}