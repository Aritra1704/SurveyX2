package `in`.zippr.surveyx2.ui.screens.base

import `in`.zippr.surveyx2.R
import `in`.zippr.surveyx2.common.AppConst
import `in`.zippr.surveyx2.common.AppInstance
import `in`.zippr.surveyx2.dependencyinjection.components.ControllerComponent
import `in`.zippr.surveyx2.dependencyinjection.modules.ControllerModule
import `in`.zippr.surveyx2.ui.dialogs.*
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Typeface
import android.location.LocationManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.DisplayMetrics
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.UiThread
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import com.arpaul.utilitieslib.BuildConfig
import com.arpaul.utilitieslib.PermissionUtils
import com.arpaul.utilitieslib.ValidationUtils
import kotlinx.android.synthetic.main.activity_splash.*
import java.util.ArrayList

abstract class BaseActivity : AppCompatActivity(), CustomDialog.DialogClick {

    private var progressDialog: ProgressDialog? = null
    private var customDialog: CustomDialog? = null
    private var singleDialog: SingleClickDialog? = null
    private var mIsControllerComponentUsed = false

    lateinit var mDialogsManager: DialogsManager
    lateinit var mDialogsFactory: DialogsFactory
    lateinit var controller: ControllerComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        controller = getControllerComponent()
        controller.injectActivity(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)

        mDialogsFactory = controller.getDialogsFactory()
        mDialogsManager = controller.getDialogManager()
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    @UiThread
    protected fun getControllerComponent(): ControllerComponent {
        check(!mIsControllerComponentUsed) { "must not use ControllerComponent more than once" }
        mIsControllerComponentUsed = true
        return (application as AppInstance)
            .getAppComponent()
            .newControllerComponent(ControllerModule(this))
    }

    fun isGpsEnabled(): Boolean {
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    }

    fun vibrateDevice() {
        val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            //deprecated in API 26
            vibrator.vibrate(500)
        }
    }

    fun checkIfReadExternalStoragePermissionGranted(): Boolean {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
            return true
        if (PermissionUtils().checkPermission(
                this@BaseActivity, arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE)
            ) == PackageManager.PERMISSION_GRANTED
        )
            return true
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                this@BaseActivity, android.Manifest.permission.READ_EXTERNAL_STORAGE
            )
        ) {
            AlertDialog.Builder(this)
                .setTitle("Hamlet")
                .setMessage("Access required for reading external storage files")
                .setPositiveButton("Ok") { dialog, which ->
                    PermissionUtils().requestPermission(
                        this@BaseActivity,
                        arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                        AppConst().PERM_READ_EXTERNAL_STORAGE
                    )
                }.create().show()
        } else {
            PermissionUtils().requestPermission(
                this@BaseActivity,
                arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                AppConst().PERM_READ_EXTERNAL_STORAGE
            )
        }
        return false
    }

    fun isPhNoValid(phoneNo: String): Boolean {
        return ValidationUtils.validatePhoneNumber(phoneNo)
    }

    fun isPasswordValid(password: String): Boolean {
        return password.length >= AppConst().PASSWORD_LENGTH
    }

    fun showLoader(isCancelable: Boolean, is_from: String) {
        try {
            progressDialog = mDialogsFactory.progressDialog(isCancelable)
            controller.injectDialog(progressDialog!!)
            mDialogsManager.showDialog(progressDialog!!, null)

//            progressDialog = ProgressDialog(this, isCancelable)
//            runOnUiThread(progressDialog)
        } catch (e: Exception) {
//            Crashlytics.log(Log.ERROR, "Loader without title", is_from)
        }
    }

    /**
     * Shows Indefinite Progress Dialog.
     *
     * @param title
     * @param message
     * @param isCancelable
     */
    fun showLoader(title: String, message: String, isCancelable: Boolean) {
        try {
            progressDialog = mDialogsFactory.progressDialog(title, message, isCancelable, false)
            controller.injectDialog(progressDialog!!)
            mDialogsManager.showDialog(progressDialog!!, null)

//            progressDialog = ProgressDialog(this, title, message, isCancelable)
//            runOnUiThread(progressDialog)
        } catch (e: Exception) {
//            Crashlytics.log(Log.ERROR, "Loader without title", is_from)
        }
    }

    /**
     * Shows Indefinite Progress Dialog.
     *
     * @param title
     * @param message
     * @param progress
     * @param isCancelable
     */
    fun showLoader(title: String, message: String, progress: Int, isCancelable: Boolean) {
        try {
            progressDialog = mDialogsFactory.progressDialog(title, message, progress, isCancelable, false)
            controller.injectDialog(progressDialog!!)
            mDialogsManager.showDialog(progressDialog!!, null)
        } catch (e: Exception) {

        }
//        progressDialog = ProgressDialog(this, title, message, progress, isCancelable)
//        runOnUiThread(progressDialog)
    }

    fun hideLoader() {
        runOnUiThread {
            try {
                val dialog = progressDialog
                if (dialog != null && dialog.isShowing())
                    dialog.dismissDialog()

                progressDialog = null
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    /**
     * Shows Dialog with user defined buttons.
     *
     * @param title
     * @param message
     * @param okButton
     * @param noButton
     * @param from
     * @param isCancelable
     */
    fun showCustomDialog(
        title: String,
        message: String,
        okButton: String,
        noButton: String,
        neutralButton: String,
        from: String,
        isCancelable: Boolean
    ) {
        customDialog = mDialogsFactory.customDialog(title, message, okButton, noButton, neutralButton, from, isCancelable, this)
        controller.injectDialog(customDialog!!)
        mDialogsManager.showDialog(customDialog!!, null)
    }

    fun hideCustomDialog() {
        runOnUiThread {
            val dialog = customDialog
            if (dialog != null && dialog.isShowing())
                dialog.dismissDialog()
        }
    }

    /*
    Hide Window Keyboard.
     */
    fun hideKeyBoard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    override fun dialogYesClick(from: String) {
        if (from.equals("", ignoreCase = true)) {

        }
    }

    override fun dialogNoClick(from: String) {
        if (from.equals("", ignoreCase = true)) {

        }
    }

    override fun dialogNeutralClick(from: String) {
        if (from.equals("", ignoreCase = true)) {

        }
    }

    /**
     * Shows Single click string list
     *
     * @param title
     * @param list
     * @param isCancelable
     * @param selectlistener
     */
    fun showSingleClickListDialog(
        title: String,
        list: ArrayList<String>,
        isCancelable: Boolean,
        listener: OnSelectedListener
    ) {
        singleDialog = mDialogsFactory.singleClickDialog(title, list, isCancelable)
        singleDialog!!.setOnSelectedListener(listener)

//        (object : SingleClickDialog.OnSelectedListener {
//            override fun onSelect(position: Int) {
//                tvText.text = list.get(position)
//            }
//        })

        controller.injectDialog(singleDialog!!)
        mDialogsManager.showDialog(singleDialog!!, null)
    }

    fun getParentView(v: View?): ViewGroup? {
        var vg: ViewGroup? = null

        if (v != null)
            vg = v.rootView as ViewGroup

        return vg
    }

    fun applyTypeface(v: ViewGroup?, f: Typeface, style: Int) {
        if (v != null) {
            val vgCount = v.childCount
            for (i in 0 until vgCount) {
                if (v.getChildAt(i) == null) continue
                if (v.getChildAt(i) is ViewGroup)
                    applyTypeface(v.getChildAt(i) as ViewGroup, f, style)
                else {
                    val view = v.getChildAt(i)
                    (view as? TextView)?.setTypeface(f, style) ?: ((view as? EditText)?.setTypeface(f, style)
                        ?: (view as? Button)?.setTypeface(f, style))
                }
            }
        }
    }

    fun getScreenWidth(): Int {
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.widthPixels
    }

    fun getScreenHeight(): Int {
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.heightPixels
    }

    fun sendDeviceDetails() {
        val message = StringBuilder()
        message.append("Board: ").append(android.os.Build.BOARD).append('\n')
        message.append("Brand: ").append(android.os.Build.BRAND).append('\n')
        message.append("Device: ").append(android.os.Build.DEVICE).append('\n')
        message.append("Host: ").append(android.os.Build.HOST).append('\n')
        message.append("ID: ").append(android.os.Build.ID).append('\n')
        message.append("Model: ").append(android.os.Build.MODEL).append('\n')
        message.append("Product: ").append(android.os.Build.PRODUCT).append('\n')

        //        logFireAnalytic(FIRE_DEVICE_DETAIL, message.toString(), FIRE_CT_TEXT);
    }

    fun shareApplication() {
        try {
            val shareIntent: Intent
            shareIntent = Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name));
            var shareMessage = "\nLet me recommend you this application\n\n" as String
            shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" +
                    BuildConfig.APPLICATION_ID + "\n\n";
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
            startActivity(Intent.createChooser(shareIntent, "choose one"));
        } catch (e : Exception) {
            e.toString();
        }
    }
}
