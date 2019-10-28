package `in`.zippr.surveyx2.dependencyinjection.components

import `in`.zippr.surveyx2.common.AppInstance
import `in`.zippr.surveyx2.common.AppPreference
import `in`.zippr.surveyx2.dependencyinjection.modules.ApplicationModule
import `in`.zippr.surveyx2.dependencyinjection.modules.ControllerModule
import `in`.zippr.surveyx2.dependencyinjection.modules.PrefModule
import `in`.zippr.surveyx2.dependencyinjection.scopes.ApplicationScope
import android.content.Context
import dagger.Component


/**
 * Component providing inject() methods for presenters.
 */
// https://medium.com/tompee/dagger-2-scopes-and-subcomponents-d54d58511781
// https://www.youtube.com/watch?v=cx6pCIbOqtI&feature=youtu.be
@ApplicationScope
@Component(modules = [ApplicationModule::class, PrefModule::class])
interface AppComponent {

    fun inject(instance: AppInstance)

    fun getAppPref(): AppPreference

    fun newControllerComponent(controllerModule: ControllerModule): ControllerComponent
}