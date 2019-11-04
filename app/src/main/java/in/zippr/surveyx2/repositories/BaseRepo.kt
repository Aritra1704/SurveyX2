package `in`.zippr.surveyx2.repositories

import `in`.zippr.surveyx2.dependencyinjection.components.DaggerRetrofitComponent
import `in`.zippr.surveyx2.dependencyinjection.components.RetrofitComponent
import `in`.zippr.surveyx2.dependencyinjection.modules.RetrofitModule
import `in`.zippr.surveyx2.webservices.APICall
import io.reactivex.disposables.Disposable
import javax.inject.Inject
import io.reactivex.disposables.CompositeDisposable



open class BaseRepo {
    @Inject
    protected lateinit var apiCall: APICall

    lateinit var subscription: Disposable
    val compositeDisposable = CompositeDisposable()

    private val injector: RetrofitComponent = DaggerRetrofitComponent
        .builder()
        .retrofitModule(RetrofitModule())
        .build()

    init {
        injector.inject(this)
    }
}