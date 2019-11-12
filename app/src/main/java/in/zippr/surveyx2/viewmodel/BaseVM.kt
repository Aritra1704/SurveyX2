package `in`.zippr.surveyx2.viewmodel

import `in`.zippr.surveyx2.common.AppInstance
import `in`.zippr.surveyx2.dependencyinjection.components.*
import `in`.zippr.surveyx2.dependencyinjection.modules.ApplicationModule
import `in`.zippr.surveyx2.dependencyinjection.modules.ApplicationModule_GetApplicationFactory.getApplication
import `in`.zippr.surveyx2.dependencyinjection.modules.ControllerModule
//import `in`.zippr.surveyx2.daggerobjects.components.RetrofitComponent
import `in`.zippr.surveyx2.dependencyinjection.modules.RetrofitModule
import `in`.zippr.surveyx2.dependencyinjection.modules.ViewModelModule
import `in`.zippr.surveyx2.repositories.BaseRepo
import `in`.zippr.surveyx2.webservices.APICall
import android.app.Application
import androidx.lifecycle.ViewModel
import javax.inject.Inject

// https://proandroiddev.com/dagger-2-part-ii-custom-scopes-component-dependencies-subcomponents-697c1fa1cfc

open class BaseVM() : ViewModel() {
    @Inject
    protected lateinit var baseRepo: BaseRepo

    private val appInjector: AppComponent = DaggerAppComponent
        .builder()
        .build()

    private val injector: ViewModelComponent = appInjector
        .newViewModelComponent(ViewModelModule())


//    private val injector: ViewModelComponent = DaggerViewModelComponent
//        .builder()
//        .viewModelModule(ViewModelModule())
//        .appComponent(appComponent)
//        .build()


//    protected fun getControllerComponent(): ViewModelComponent {
////        check(!mIsControllerComponentUsed) { "must not use ControllerComponent more than once" }
////        mIsControllerComponentUsed = true
//        return (application as AppInstance)
//            .getAppComponent()
//            .newViewModelComponent(ViewModelModule())
//    }

    init {
        injector.inject(this)
    }
}