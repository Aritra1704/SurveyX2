package `in`.zippr.surveyx2.viewmodel

import `in`.zippr.surveyx2.R
import `in`.zippr.surveyx2.models.request.LoginRequest
import `in`.zippr.surveyx2.models.response.*
import `in`.zippr.surveyx2.repositories.login.LoginRepo
import `in`.zippr.surveyx2.utils.IdResourceString
import `in`.zippr.surveyx2.utils.ResourceString
import `in`.zippr.surveyx2.utils.SingleLiveEvent
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations

class LoginVM constructor(val loginRepo: LoginRepo) : BaseVM() {

    val showLoader = MutableLiveData<Int>()
//    var usersLD: MutableLiveData<Resource<UserResponse>>
//    fun <T : Any?> MutableLiveData<T>.default(initialValue: T) = apply { setValue(initialValue) }
//    var usersLD = MutableLiveData<ApiResponse<UserResponse>>().default(initialValue = )// apply { postValue("initial value") }
    var usersLD: LiveData<UserResponse?>
    var usersResponse =  MutableLiveData<ApiResponse<UserResponse>>()
    var userRequest: MutableLiveData<LoginRequest>
    val loadData : SingleLiveEvent<Unit> =
        SingleLiveEvent()
    internal val toastMessage = SingleLiveEvent<ResourceString>()
    val userIdWatcher = ObservableField<TextWatcher>()
    val passwordWatcher = ObservableField<TextWatcher>()


    init {
        userRequest = MutableLiveData()
        loginRepo.performLogin2(userRequest.value!!, usersResponse)
        usersLD = Transformations.switchMap(loadData) {
            switchMaporLoginResponse(usersResponse,
                doOnSuccess = {
                    showLoader.value = View.GONE
                    return@switchMaporLoginResponse it
                },
                doOnSubscribe = {
                    showLoader.value = View.VISIBLE
                },
                doOnError = {
                    showLoader.value = View.GONE
                })
        }
//        usersLD = Transformations.switchMap(loadData) {
//            switchMaporLoginResponse(loginRepo.performLogin2(userRequest.value!!),
//                doOnSuccess = {
//                    showLoader.value = View.GONE
//                    return@switchMaporLoginResponse it
//                },
//                doOnSubscribe = {
//                    showLoader.value = View.VISIBLE
//                },
//                doOnError = {
//                    showLoader.value = View.GONE
//                })
//        }
        userIdWatcher.set(object: TextWatcher {
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(s: Editable?) {
                var user = userRequest.value
                user?.let {
                    it.userId = s.toString()
                    userRequest.value = it
                }
            }
        })
        passwordWatcher.set(object: TextWatcher {
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(s: Editable?) {
                var user = userRequest.value
                user?.let {
                    it.password = s.toString()
                    userRequest.value = it
                }
            }
        })
    }

    fun performLogin() {
        if(validateUserId())
            loadData.call()
    }

    fun <T, X> switchMaporLoginResponse(liveData: LiveData<ApiResponse<T>>,
                                        doOnSubscribe: (() -> Unit)? = null,
                                        doOnSuccess: (((T?) -> X?)?) = null,
                                        doOnError: (((Throwable) -> Unit)?) = null): LiveData<X?>? {
        val response = Transformations.map(liveData) {
            when (it) {
                is ApiIsLoading -> {
                    doOnSubscribe?.invoke()
                    null
                }
                is ApiSuccessResponse -> {
                    val responseBody = it.body
                    doOnSuccess?.invoke(responseBody)
                }
                is ApiEmptyResponse<*> -> {
                    doOnSuccess?.invoke(null)
                    null
                }
                is ApiErrorResponse<*> -> {
                    doOnError?.invoke(it.errorMessage)
                    null
                }
                else -> null
            }
        }
        return response
    }
    fun validateUserId(): Boolean {
        var user = userRequest.value
        var result = true
        user?.let {
            if(TextUtils.isEmpty(user.userId)) {
                toastMessage.value = IdResourceString(R.string.err_userid_empty)
                result = false
            } else if(TextUtils.isEmpty(user.password)) {
                toastMessage.value = IdResourceString(R.string.err_password_empty)
                result = false
            }
        }
        return result
    }
    fun getUserId() {

    }
//    private lateinit var subscription: Disposable
//
//    private var employeeListLD: MutableLiveData<Resource<List<User>>>? = null
//    var employeeLD: MutableLiveData<Resource<User>>? = null
//
//
//    init {
//
//    }
//    fun getEmployeesMutable(): MutableLiveData<Resource<List<User>>>? {
//        employeeListLD = MutableLiveData()
//        return employeeListLD
//    }
//
//    fun getSingleEmployeeMutable(): MutableLiveData<Resource<User>>? {
//        employeeLD = MutableLiveData()
//        return employeeLD
//    }
//
//    fun loadEmployees() {
//        subscription = apiCall.getAllEmployees()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .doOnSubscribe { onApiLoading("GET_ALL") }
//            .subscribe(
//                { result -> onEmployeesSuccess(result) },
//                { error -> onEmployeesError(error) }
//            )
//    }
//
//    fun loadOneEmployee(employeeId: String) {
//        subscription = apiCall.getOneEmployees(employeeId)
//            .toFlowable()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .doOnSubscribe { onApiLoading("GET_ONE") }
//            .subscribe(
//                { result -> onSingleEmployeesSuccess(result) },
//                { error -> onSingleEmployeesError(error) }
//            )
//    }
//
//    fun createEmployee(employee: User) {
//        subscription = apiCall.createEmployee(employee)
//            .toFlowable()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .doOnSubscribe { onApiLoading("CREATE") }
//            .subscribe(
//                { result -> onSingleEmployeesSuccess(result) },
//                { error -> onSingleEmployeesError(error) }
//            )
//    }
//
//    private fun onApiLoading(from: String){
//        when(from) {
//            "GET_ALL" -> employeeListLD!!.value = Resource.loading(null)
//            "GET_ONE" -> employeeLD!!.value = Resource.loading(null)
//            "CREATE" -> employeeLD!!.value = Resource.loading(null)
//        }
//    }
//
//    private fun onEmployeesSuccess(employees:List<User>){
//        employeeListLD!!.value = Resource.success(employees)
//    }
//
//    private fun onEmployeesError(error: Throwable){
//        employeeListLD!!.value = Resource.error(error.message, null)
//    }
//
//    private fun onSingleEmployeesSuccess(employee:User){
//        employeeLD!!.value = Resource.success(employee)
//    }
//
//    private fun onSingleEmployeesError(error: Throwable){
//        employeeLD!!.value = Resource.error(error.message, null)
//
//    }
}
