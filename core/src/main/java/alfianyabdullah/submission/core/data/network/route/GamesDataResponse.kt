package alfianyabdullah.submission.core.data.network.route

sealed class GamesDataResponse<out R> {
    data class Success<out T>(val data: T) : GamesDataResponse<T>()
    data class Error(val errorMessage: String) : GamesDataResponse<Nothing>()
    object Empty : GamesDataResponse<Nothing>()
}