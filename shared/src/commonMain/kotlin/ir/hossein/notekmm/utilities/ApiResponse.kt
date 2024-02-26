package ir.hossein.notekmm.utilities

sealed class ApiResponse<T> {

    data class OnSuccess<T>(val data: T): ApiResponse<T>()
    data class OnFailure<T>(val message: String): ApiResponse<T>()
}