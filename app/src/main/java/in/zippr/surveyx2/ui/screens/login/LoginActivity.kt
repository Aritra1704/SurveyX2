package `in`.zippr.surveyx2.ui.screens.login

import `in`.zippr.surveyx2.R
import `in`.zippr.surveyx2.common.AppInstance
import `in`.zippr.surveyx2.databinding.ActivityLoginBinding
import `in`.zippr.surveyx2.ui.screens.base.BaseActivity
import `in`.zippr.surveyx2.viewmodel.BaseViewModelFactory
import `in`.zippr.surveyx2.viewmodel.DaggerViewModelFactory
import `in`.zippr.surveyx2.viewmodel.LoginVM
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import org.jetbrains.anko.toast
import javax.inject.Inject

// https://android.jlelse.eu/lets-keep-activity-dumb-using-livedata-53468ed0dc1f

class LoginActivity : BaseActivity() {

    @Inject
    lateinit var viewModeFactory: DaggerViewModelFactory
    lateinit var loginVM: LoginVM
//    val loginVM: LoginVM by lazy {
//        ViewModelProviders.of(this, BaseViewModelFactory{ LoginVM() }).get(LoginVM::class.java)
//    }
    private lateinit var binding : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         initDataBinding()
//        setContentView(R.layout.activity_login)

//        loginVM = ViewModelProviders.of(this@LoginActivity).get(LoginVM::class.java)
        loginVM.toastMessage.observe(this, Observer { res ->
            res?.let {
                val message = res.format(this@LoginActivity)
                toast(message).show()
            }
        })
    }

    private fun initDataBinding() {
//        viewModeFactory = controller.getViewModelFactory()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.setLifecycleOwner(this)
//        loginVM = ViewModelProviders.of(this@LoginActivity, BaseViewModelFactory()).get(LoginVM::class.java)
        loginVM = ViewModelProviders.of(this, BaseViewModelFactory{ LoginVM() }).get(LoginVM::class.java)
        binding.vm = loginVM
    }
}
