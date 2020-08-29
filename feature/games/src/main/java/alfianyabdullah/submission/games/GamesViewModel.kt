package alfianyabdullah.submission.games

import alfianyabdullah.submission.core.data.Resource
import alfianyabdullah.submission.core.domain.model.Game
import alfianyabdullah.submission.core.domain.usecase.NetworkTaskUseCase
import androidx.lifecycle.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class GamesViewModel(private val useCase: NetworkTaskUseCase) : ViewModel() {

    private val _games = MutableLiveData<Resource<List<Game>>>()
    val games: LiveData<Resource<List<Game>>> = _games

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        _isLoading.postValue(false)
        findAllGames()
    }

    fun findAllGames() {
        viewModelScope.launch {
            useCase.getAllGame()
                .onStart { _isLoading.postValue(true) }
                .onCompletion { _isLoading.postValue(false) }
                .collect {
                    _games.postValue(it)
                }

        }
    }
}