package `in`.zippr.surveyx2.viewmodel

import `in`.zippr.surveyx2.common.Resource
import `in`.zippr.surveyx2.dependencyinjection.data.User
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class EmployeeVM : BaseVM() {

    private lateinit var subscription: Disposable

    private var employeeListLD: MutableLiveData<Resource<List<User>>>? = null
    private var employeeLD: MutableLiveData<Resource<User>>? = null


    fun getEmployeesMutable(): MutableLiveData<Resource<List<User>>>? {
        employeeListLD = MutableLiveData()
        return employeeListLD
    }

    fun getSingleEmployeeMutable(): MutableLiveData<Resource<User>>? {
        employeeLD = MutableLiveData()
        return employeeLD
    }

    fun loadEmployees() {
        subscription = apiCall.getAllEmployees()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onApiLoading("GET_ALL") }
            .subscribe(
                { result -> onEmployeesSuccess(result) },
                { error -> onEmployeesError(error) }
            )
    }

    fun loadOneEmployee(employeeId: String) {
        subscription = apiCall.getOneEmployees(employeeId)
            .toFlowable()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onApiLoading("GET_ONE") }
            .subscribe(
                { result -> onSingleEmployeesSuccess(result) },
                { error -> onSingleEmployeesError(error) }
            )
    }

    fun createEmployee(employee: User) {
        subscription = apiCall.createEmployee(employee)
            .toFlowable()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onApiLoading("CREATE") }
            .subscribe(
                { result -> onSingleEmployeesSuccess(result) },
                { error -> onSingleEmployeesError(error) }
            )
    }

    private fun onApiLoading(from: String){
        when(from) {
            "GET_ALL" -> employeeListLD!!.value = Resource.loading(null)
            "GET_ONE" -> employeeLD!!.value = Resource.loading(null)
            "CREATE" -> employeeLD!!.value = Resource.loading(null)
        }
    }

    private fun onEmployeesSuccess(employees:List<User>){
        employeeListLD!!.value = Resource.success(employees)
    }

    private fun onEmployeesError(error: Throwable){
        employeeListLD!!.value = Resource.error(error.message, null)
    }

    private fun onSingleEmployeesSuccess(employee:User){
        employeeLD!!.value = Resource.success(employee)
    }

    private fun onSingleEmployeesError(error: Throwable){
        employeeLD!!.value = Resource.error(error.message, null)

    }
}
