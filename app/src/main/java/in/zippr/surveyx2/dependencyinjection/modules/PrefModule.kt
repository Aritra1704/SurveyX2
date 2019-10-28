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

//    @Provides
//    @ApplicationScope
//    internal fun providePrefInterface(app: Application): AppPreference {
//        return AppPreference(app)
//    }

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

//    @ApplicationScope
//    @Provides
//    fun providePreferences(): SharedPreferences {
//        return context.getSharedPreferences(
//            context.getString(R.string.app_name),
//            Context.MODE_PRIVATE
//        )
//    }
}