package `in`.zippr.surveyx2.repositories

import `in`.zippr.surveyx2.common.AppInstance
import `in`.zippr.surveyx2.dependencyinjection.components.AppComponent
import `in`.zippr.surveyx2.dependencyinjection.components.DaggerAppComponent
import `in`.zippr.surveyx2.dependencyinjection.components.RetrofitComponent
import `in`.zippr.surveyx2.dependencyinjection.modules.RetrofitModule
import `in`.zippr.surveyx2.webservices.APICall
import io.reactivex.disposables.Disposable
import javax.inject.Inject
import io.reactivex.disposables.CompositeDisposable

open class BaseRepo() {
    @Inject
    protected lateinit var apiCall: APICall

    lateinit var subscription: Disposable
    val compositeDisposable = CompositeDisposable()

    private val appInjector: AppComponent = DaggerAppComponent
        .builder()
        .build()

    private val injector: RetrofitComponent = appInjector
        .newRepositoryComponent(RetrofitModule())

//    DaggerRetrofitComponent
//        .builder()
//        .retrofitModule(RetrofitModule())
////        .appComponent(AppInstance.app.appComponent)
//        .build()

    init {
//        injector = appInjector
//            .newRepositoryComponent(RetrofitModule())

        injector.inject(this)
    }
}