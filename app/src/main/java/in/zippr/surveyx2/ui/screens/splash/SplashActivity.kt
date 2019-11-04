package `in`.zippr.surveyx2.ui.screens.splash

import `in`.zippr.surveyx2.BuildConfig
import `in`.zippr.surveyx2.R
import `in`.zippr.surveyx2.ui.dialogs.*
import `in`.zippr.surveyx2.ui.screens.LoginActivity
import `in`.zippr.surveyx2.ui.screens.base.BaseActivity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.core.os.HandlerCompat
import kotlinx.android.synthetic.main.activity_splash.*
import java.util.ArrayList

class SplashActivity : BaseActivity() {

    val TAG: String = SplashActivity::class.java.simpleName
    var timeout = 1500

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        moveToLogin()
    }

    fun moveToLogin() {
        if (!BuildConfig.DEBUG)
            timeout = 1500

        Handler().postDelayed(object: Runnable {
            override fun run() {
                startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
            }
        }, timeout.toLong())
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
