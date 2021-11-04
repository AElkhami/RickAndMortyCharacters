package com.elkhamitech.rickandmortycharacters.data.other

/**
 * Created by A.Elkhami on 01,November,2021
 */
sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null,
    val status: Status
) {
    class Success<T>(data: T) : Resource<T>(data = data, status = Status.SUCCESS)
    class Loading<T>() : Resource<T>(status = Status.LOADING)
    class Error<T>(data: T? = null, message: String) :
        Resource<T>(data = data, message = message, status = Status.FAILED)
}

enum class Status {
    SUCCESS,
    FAILED,
    LOADING
}