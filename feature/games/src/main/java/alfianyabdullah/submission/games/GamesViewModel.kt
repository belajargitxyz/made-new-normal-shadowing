package alfianyabdullah.submission.games

import alfianyabdullah.submission.core.data.Resource
import alfianyabdullah.submission.core.domain.model.Game
import androidx.lifecycle.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class GamesViewModel(private val useCase: GamesUseCase) : ViewModel() {

    private val _games = MutableLiveData<Resource<List<Game>>>()
    private val _isLoading = MutableLiveData<Boolean>()

    init {
        _isLoading.postValue(false)
        findAllGames()
    }

    val games: LiveData<Resource<List<Game>>>
        get() = _games

    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private fun findAllGames() {
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