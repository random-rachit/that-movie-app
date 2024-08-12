package com.rachitbhutani.thatmovieapp.networking

import com.rachitbhutani.thatmovieapp.data.NO_INTERNET_MESSAGE
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

suspend fun <T: Any> handleApi(execute: suspend () -> Response<T>): NetworkResponse<T> {
    return try {
        val response = execute.invoke()
        val body = response.body()
        if (response.isSuccessful && body != null) {
            NetworkResponse.Success(body)
        } else {
            NetworkResponse.Error(
                ErrorResponse(
                    response.errorBody().toString(),
                    response.code()
                )
            )
        }
    } catch (e: HttpException) {
        e.printStackTrace()
        NetworkResponse.Error(ErrorResponse(e.message(), e.code()))
    } catch (e: IOException) {
        NetworkResponse.Error(ErrorResponse(message = NO_INTERNET_MESSAGE))
    } catch (e: Exception) {
        NetworkResponse.Error(ErrorResponse(message = e.message))
    }
}