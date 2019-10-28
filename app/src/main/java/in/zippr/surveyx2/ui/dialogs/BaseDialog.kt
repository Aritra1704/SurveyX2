package `in`.zippr.surveyx2.ui.dialogs

import `in`.zippr.surveyx2.common.AppInstance
import `in`.zippr.surveyx2.dependencyinjection.components.ControllerComponent
import `in`.zippr.surveyx2.dependencyinjection.modules.ControllerModule
import androidx.annotation.UiThread
import androidx.appcompat.app.AppCompatDialogFragment

abstract class BaseDialog: AppCompatDialogFragment() {
    private var mIsControllerComponentUsed = false

    @UiThread
    protected fun getControllerComponent(): ControllerComponent {
        check(!mIsControllerComponentUsed) { "must not use ControllerComponent more than once" }
        mIsControllerComponentUsed = true
        return (activity!!.application as AppInstance)
            .getAppComponent()
            .newControllerComponent(ControllerModule(activity!!))
    }

    /**
     * Get this dialog's ID that was supplied with a call to
     * [DialogsManager.showDialogWithId]
     * @return dialog's ID, or null if none was set
     */
    protected fun getDialogId(): String? {
        return if (arguments == null) {
            null
        } else {
            arguments!!.getString(DialogsManager.ARGUMENT_DIALOG_ID)
        }
    }
}