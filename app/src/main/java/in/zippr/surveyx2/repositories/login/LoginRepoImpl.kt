package `in`.zippr.surveyx2.repositories.login

import `in`.zippr.surveyx2.models.request.LoginRequest
import `in`.zippr.surveyx2.models.response.ApiResponse
import `in`.zippr.surveyx2.models.response.UserResponse
import `in`.zippr.surveyx2.repositories.BaseRepo
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LoginRepoImpl: BaseRepo(), LoginRepo {

//    override fun performLogin(value: LoginRequest): LiveData<ApiResponse<UserResponse>> {
//
//        subscription = apiCall.performLogin(value.userId, value.password)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .doOnSubscribe { onApiLoading("GET_ALL") }
//            .subscribe(
//                { result -> onEmployeesSuccess(result) },
//                { error -> onEmployeesError(error) }
//            )
//        compositeDisposable.add(subscription)
//        return subscription.
//    }

    override fun performLogin2(value: LoginRequest, usersResponse: MutableLiveData<ApiResponse<UserResponse>>) {
        subscription = apiCall.performLogin("", value)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { usersResponse.value = ApiResponse.createLoadingResponse() }
            .subscribe(
                { result -> usersResponse.value = result },
                { error -> usersResponse.value = ApiResponse.Companion.create(error) }
            )
        compositeDisposable.add(subscription)
    }
//    override fun performLogin(value: LoginRequest?): LiveData<ApiResponse<UserResponse>> {
//
//    }
}