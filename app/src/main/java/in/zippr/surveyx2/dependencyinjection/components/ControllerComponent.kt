package `in`.zippr.surveyx2.dependencyinjection.components

import `in`.zippr.surveyx2.dependencyinjection.modules.ControllerModule
import `in`.zippr.surveyx2.dependencyinjection.scopes.UIScope
import `in`.zippr.surveyx2.ui.dialogs.BaseDialog
import `in`.zippr.surveyx2.ui.dialogs.DialogsFactory
import `in`.zippr.surveyx2.ui.dialogs.DialogsManager
import `in`.zippr.surveyx2.ui.screens.base.BaseActivity
import androidx.fragment.app.FragmentManager
import dagger.Subcomponent

@UIScope
@Subcomponent(modules = [ControllerModule::class])
interface ControllerComponent {

    fun injectActivity(activity: BaseActivity)

    fun getDialogManager(): DialogsManager

    fun getDialogsFactory(): DialogsFactory

    fun injectDialog(dialog: BaseDialog)
}