package `in`.zippr.surveyx2.repositories.login

import `in`.zippr.surveyx2.models.request.LoginRequest
import `in`.zippr.surveyx2.models.response.ApiResponse
import `in`.zippr.surveyx2.models.response.UserResponse
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

interface LoginRepo {
    abstract fun performLogin2(value: LoginRequest, usersResponse: MutableLiveData<ApiResponse<UserResponse>>)
}