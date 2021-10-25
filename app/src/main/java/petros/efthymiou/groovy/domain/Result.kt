package petros.efthymiou.groovy.domain

sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val message: String?, val statusCode: Int? = null) :
        Result<Nothing>()

    class Loading<T> : Result<T>()
}
