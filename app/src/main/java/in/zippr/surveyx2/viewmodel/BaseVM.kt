package `in`.zippr.surveyx2.viewmodel

import `in`.zippr.surveyx2.dependencyinjection.components.DaggerRetrofitComponent
import `in`.zippr.surveyx2.dependencyinjection.components.RetrofitComponent
//import `in`.zippr.surveyx2.daggerobjects.components.RetrofitComponent
import `in`.zippr.surveyx2.dependencyinjection.modules.RetrofitModule
import `in`.zippr.surveyx2.webservices.APICall
import androidx.lifecycle.ViewModel
import javax.inject.Inject

// https://proandroiddev.com/dagger-2-part-ii-custom-scopes-component-dependencies-subcomponents-697c1fa1cfc

abstract class BaseVM : ViewModel() {
    @Inject
    protected lateinit var apiCall: APICall

    private val injector: RetrofitComponent = DaggerRetrofitComponent
        .builder()
        .retrofitModule(RetrofitModule())
        .build()

    init {
        injector.inject(this)
    }

}