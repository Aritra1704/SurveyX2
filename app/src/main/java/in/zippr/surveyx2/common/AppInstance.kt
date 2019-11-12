package `in`.zippr.surveyx2.common

import `in`.zippr.surveyx2.dependencyinjection.components.AppComponent
import `in`.zippr.surveyx2.dependencyinjection.components.DaggerAppComponent
import `in`.zippr.surveyx2.dependencyinjection.modules.ApplicationModule
import `in`.zippr.surveyx2.dependencyinjection.modules.PrefModule
import android.app.Application
import androidx.annotation.UiThread
import javax.inject.Inject

// https://medium.com/@Zhuinden/that-missing-guide-how-to-use-dagger2-ef116fbea97

class AppInstance  : Application() {

    val TAG = AppInstance::class.java.simpleName

    @Inject
    lateinit var appPref: AppPreference

    @Inject
    lateinit var mLogger: MyLogger

    val component: AppComponent by lazy {
        DaggerAppComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .prefModule(PrefModule(this))
            .build()
    }

    override fun onCreate() {
        component.inject(this)
        super.onCreate()

//        appPref = component.getAppPref()
    }

    @UiThread
    fun getAppComponent(): AppComponent {
        return component
    }
}