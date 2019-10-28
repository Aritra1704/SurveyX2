package `in`.zippr.surveyx2.webservices

import `in`.zippr.surveyx2.dependencyinjection.data.User
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.POST
import java.util.ArrayList

interface APICall {
    @GET("employees")
    abstract fun getAllEmployees(): Observable<ArrayList<User>>

    @GET("employee/:id")
    abstract fun getOneEmployees(id: String): Single<User>

    @POST("create")
    abstract fun createEmployee(employee: User): Single<User>
}