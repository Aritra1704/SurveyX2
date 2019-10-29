package `in`.zippr.surveyx2.ui

import `in`.zippr.surveyx2.R
import `in`.zippr.surveyx2.dependencyinjection.components.ControllerComponent
import `in`.zippr.surveyx2.ui.dialogs.*
import `in`.zippr.surveyx2.ui.screens.base.BaseActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList
import javax.inject.Inject

class MainActivity : BaseActivity() {

    val TAG: String = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvText.setOnClickListener(View.OnClickListener {
            tvText.text = it.toString()
        })
    }

    fun onClicked(view: View) {
        val list = ArrayList<String>()
        list.add("Test1")
        list.add("Test2")
        list.add("Test3")

        showSingleClickListDialog("Test", list, false, object : OnSelectedListener {
            override fun onSelect(position: Int) {
                tvText.text = list.get(position)
            }
        })

//        showLoader("Test", "Loading", false)

//        showCustomDialog("Test","Test message","ok"," cancel","neutral","test",false)
    }
}
