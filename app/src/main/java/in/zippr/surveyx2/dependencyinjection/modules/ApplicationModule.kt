package `in`.zippr.surveyx2.dependencyinjection.modules

import `in`.zippr.surveyx2.common.MyLogger
import `in`.zippr.surveyx2.dependencyinjection.components.ControllerComponent
import `in`.zippr.surveyx2.dependencyinjection.scopes.ApplicationScope
import android.app.Application
import dagger.Module
import dagger.Provides

@Module//(subcomponents = [ControllerComponent::class])
class ApplicationModule {
    var mApplication: Application

    constructor(application: Application) {
        mApplication = application
    }

    @Provides
    @ApplicationScope
    fun getApplication(): Application {
        return mApplication
    }

    @Provides
    @ApplicationScope
    fun getLogger(): MyLogger {
        return MyLogger(true)
    }
}