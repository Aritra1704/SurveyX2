package `in`.zippr.surveyx2.dependencyinjection.modules

import `in`.zippr.surveyx2.common.AppPreference
import `in`.zippr.surveyx2.dependencyinjection.scopes.ApplicationScope
import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Inject

@Module
class PrefModule {

    private val mApplication: Application
    @Inject
    constructor(application: Application) {
        this.mApplication = application
    }

    @ApplicationScope
    @Provides
    fun provideAppPreference(): AppPreference {
        return AppPreference(mApplication)
    }
}