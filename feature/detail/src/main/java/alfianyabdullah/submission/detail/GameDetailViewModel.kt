package alfianyabdullah.submission.detail

import alfianyabdullah.submission.core.data.Resource
import alfianyabdullah.submission.core.domain.model.Game
import alfianyabdullah.submission.core.domain.model.GameDetail
import alfianyabdullah.submission.core.domain.usecase.LocalTaskInteractor
import alfianyabdullah.submission.core.domain.usecase.LocalTaskUseCase
import alfianyabdullah.submission.core.domain.usecase.NetworkTaskUseCase
import androidx.lifecycle.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class GameDetailViewModel(
    private val networkTaskUseCase: NetworkTaskUseCase,
    private val localTaskUseCase: LocalTaskUseCase
) : ViewModel() {

    private val _game = MutableLiveData<Resource<GameDetail>>()
    val game: LiveData<Resource<GameDetail>> = _game

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _favorite = MutableLiveData<Boolean>()
    val favorite: LiveData<Boolean> = _favorite

    init {
        _isLoading.postValue(false)
        _favorite.postValue(false)
    }

    fun findGameById(gameId: Int) {
        viewModelScope.launch {
            networkTaskUseCase.findGameById(gameId)
                .onStart { _isLoading.postValue(true) }
                .onCompletion { _isLoading.postValue(false) }
                .collect {
                    _game.postValue(it)
                }
        }
    }

    fun loadFavoriteStatus(game: Game) {
        viewModelScope.launch {
            localTaskUseCase.findGameInDatabaseById(game.id)
                .collect {
                    _favorite.postValue(it.isNotEmpty())
                }
        }
    }

    fun updateGamesFavorite(isFavorite: Boolean, game: Game) {
        viewModelScope.launch {
            if (isFavorite) {
                localTaskUseCase.removeGameFromDatabase(game)
            } else {
                localTaskUseCase.insertGameToDatabase(game)
            }

            loadFavoriteStatus(game)
        }
    }
}