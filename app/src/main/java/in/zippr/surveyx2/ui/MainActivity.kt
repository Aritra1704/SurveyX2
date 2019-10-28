package `in`.zippr.surveyx2.ui

import `in`.zippr.surveyx2.R
import `in`.zippr.surveyx2.dependencyinjection.components.ControllerComponent
import `in`.zippr.surveyx2.ui.dialogs.DialogsFactory
import `in`.zippr.surveyx2.ui.dialogs.DialogsManager
import `in`.zippr.surveyx2.ui.dialogs.SingleClickDialog
import `in`.zippr.surveyx2.ui.screens.base.BaseActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import java.util.ArrayList
import javax.inject.Inject

class MainActivity : BaseActivity() {

    val TAG: String = MainActivity::class.java.simpleName
//    val contextModule = ContextModule(this)
//    val appPref = PrefModule(this)

    @Inject
    lateinit var mDialogsManager: DialogsManager
//    @Inject
    lateinit var mDialogsFactory: DialogsFactory

    lateinit var controller: ControllerComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        controller = getControllerComponent()
        controller.injectActivity(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClicked(view: View) {
        val list = ArrayList<String>()
        list.add("Test1")
        list.add("Test2")
        list.add("Test3")

//        if(mDialogsFactory === null)
//            toast("Dialog factorry is null").show()
//
//        mDialogsFactory?.let {
//
//            var singleDialog: SingleClickDialog = it.singleClickDialog("Test", list)
//            controller.injectDialog(singleDialog)
//            mDialogsManager?.let {
//                it.showDialog(singleDialog, null)
//            }
//        }

        mDialogsFactory = DialogsFactory()
        var singleDialog: SingleClickDialog = mDialogsFactory.singleClickDialog("Test", list)
        controller.injectDialog(singleDialog)

        mDialogsManager?.let {
            it.showDialog(singleDialog, null)
        }?.run {
            Log.d(TAG, "mDialogsFactory is null")
        }
    }
}
