package `in`.zippr.surveyx2.dependencyinjection.modules

import `in`.zippr.surveyx2.ui.dialogs.BaseRunnable
import `in`.zippr.surveyx2.ui.dialogs.DialogsFactory
import `in`.zippr.surveyx2.ui.dialogs.DialogsManager
import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import dagger.Module
import dagger.Provides

@Module
class ControllerModule(activity: FragmentActivity) {
    var mActivity: FragmentActivity

    init {
        mActivity = activity
    }

    @Provides
    fun getContext(): Context {
        return mActivity
    }

    @Provides
    fun getActivity(): Activity {
        return mActivity
    }

    @Provides
    fun getFragmentManager(): FragmentManager {
        return mActivity.supportFragmentManager
    }

    @Provides
    fun getDialogManager(fragmentManager: FragmentManager): DialogsManager {
        return DialogsManager(fragmentManager)
    }

    @Provides
    fun getDialogsFactory(): DialogsFactory {
        return DialogsFactory()
    }

}