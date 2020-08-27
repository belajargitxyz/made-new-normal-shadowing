package alfianyabdullah.submission.detail

import alfianyabdullah.submission.core.data.Resource
import alfianyabdullah.submission.core.domain.model.Game
import alfianyabdullah.submission.core.domain.model.GameDetail
import alfianyabdullah.submission.core.domain.usecase.NetworkTaskUseCase
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class GameDetailViewModel(private val useCase: NetworkTaskUseCase) : ViewModel() {

    private val _game = MutableLiveData<Resource<GameDetail>>()
    val game: LiveData<Resource<GameDetail>> = _game

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        _isLoading.postValue(false)
    }

    fun findGameById(gameId: Int) {
        viewModelScope.launch {
            useCase.findGameById(gameId)
                .onStart { _isLoading.postValue(true) }
                .onCompletion { _isLoading.postValue(false) }
                .collect {
                    _game.postValue(it)
                }
        }
    }
}