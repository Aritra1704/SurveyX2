package `in`.zippr.surveyx2.dependencyinjection.components

import `in`.zippr.surveyx2.dependencyinjection.modules.ControllerModule
import `in`.zippr.surveyx2.dependencyinjection.modules.ViewModelFactoryModule
import `in`.zippr.surveyx2.dependencyinjection.modules.ViewModelModule
import `in`.zippr.surveyx2.dependencyinjection.scopes.UIScope
import `in`.zippr.surveyx2.ui.dialogs.BaseDialog
import `in`.zippr.surveyx2.ui.dialogs.DialogsFactory
import `in`.zippr.surveyx2.ui.dialogs.DialogsManager
import `in`.zippr.surveyx2.ui.screens.base.BaseActivity
import `in`.zippr.surveyx2.viewmodel.DaggerViewModelFactory
import androidx.fragment.app.FragmentManager
import dagger.Subcomponent

@UIScope
@Subcomponent(modules = [
    ControllerModule::class,
    ViewModelFactoryModule::class,
    ViewModelModule::class
])
interface ControllerComponent {

    fun injectActivity(activity: BaseActivity)

    fun getDialogManager(): DialogsManager

    fun getDialogsFactory(): DialogsFactory

    fun injectDialog(dialog: BaseDialog)

//    fun getViewModelFactory(): DaggerViewModelFactory
}