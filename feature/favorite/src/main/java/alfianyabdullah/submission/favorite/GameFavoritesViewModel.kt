package alfianyabdullah.submission.favorite

import alfianyabdullah.submission.core.domain.model.Game
import alfianyabdullah.submission.core.domain.usecase.LocalTaskUseCase
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData

class GameFavoritesViewModel(private val localTaskUseCase: LocalTaskUseCase) : ViewModel() {

    fun loadAllFavoriteGame(): LiveData<List<Game>> {
        return localTaskUseCase.getAllGameInDatabase().asLiveData()
    }
}