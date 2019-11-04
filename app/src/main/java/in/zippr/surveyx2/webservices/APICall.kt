package `in`.zippr.surveyx2.webservices

import `in`.zippr.surveyx2.models.request.LoginRequest
import `in`.zippr.surveyx2.models.response.ApiResponse
import `in`.zippr.surveyx2.models.response.UserResponse
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface APICall {
    @POST("/login")
    abstract fun performLogin(@Header("x-zippr-api-key") apikey: String,  @Body userId: LoginRequest): Observable<ApiResponse<UserResponse>>

//    @GET("employee/:id")
//    abstract fun getOneEmployees(id: String): Single<User>
//
//    @POST("create")
//    abstract fun createEmployee(employee: User): Single<User>
}