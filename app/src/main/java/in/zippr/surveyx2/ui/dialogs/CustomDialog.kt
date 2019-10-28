package `in`.zippr.surveyx2.ui.dialogs

import android.content.Context
import android.content.DialogInterface
import android.os.Handler
import android.text.TextUtils
import androidx.appcompat.app.AlertDialog

internal class CustomDialog(
    context: Context,
    strTitle: String,
    strMessage: String,
    firstBtnName: String,
    secondBtnName: String,
    from: String,
    isCancelable: Boolean,
    dialogClick: DialogClick
) : BaseRunnable() {

    private var strTitle: String? = null
    private var strMessage: String? = null
    private var firstBtnName: String? = null
    private var secondBtnName: String? = null
    private var from: String? = null
    private var isCancelable = false
    private var alertDialog: AlertDialog? = null
    private var mContext: Context? = null
    private var dialogClick: DialogClick? = null

    init {
        try {
            mContext = context
            this.strTitle = strTitle
            this.strMessage = strMessage
            this.firstBtnName = firstBtnName
            this.secondBtnName = secondBtnName
            this.isCancelable = isCancelable
            this.dialogClick = dialogClick
            if (!TextUtils.isEmpty(from)) this.from = from else this.from = ""
        } catch (e: Exception) {
//                Crashlytics.log(Log.ERROR, "Custom dialog", e.localizedMessage)
            e.printStackTrace()
        }
    }

    override fun run() {
        showCustomDialog()
    }

    private fun showCustomDialog() {
        try {
            var show = false

            val context = mContext
            if(context == null)
                return

            if (alertDialog == null) {
                alertDialog = AlertDialog.Builder(context).create()
                show = setDialog()
            } else if (alertDialog != null) {
                alertDialog?.dismiss()
                show = setDialog()
            } else
                show = setDialog()
            if (show)
                Handler().post {
                    alertDialog?.show()
                }
        } catch (e: Exception) {
//                Crashlytics.log(Log.ERROR, "Custom dialog", e.localizedMessage)
            e.printStackTrace()
        }

    }

    private fun setDialog(): Boolean {
        try {
            alertDialog?.setTitle(strTitle)
            alertDialog?.setMessage(strMessage)
            alertDialog?.setCanceledOnTouchOutside(isCancelable)
            alertDialog?.setButton(
                AlertDialog.BUTTON_POSITIVE, firstBtnName,
                DialogInterface.OnClickListener { dialog, which ->
                    var click = dialogClick
                    if(click != null) click.dialogYesClick(from.toString())
                    dialog.dismiss()
                })
            alertDialog?.setButton(
                AlertDialog.BUTTON_NEGATIVE, secondBtnName,
                DialogInterface.OnClickListener { dialog, which ->
                    var click = dialogClick
                    if(click != null) click.dialogNoClick(from.toString())
                    dialog.dismiss()
                })
        } catch (ex: Exception) {
            ex.printStackTrace()
            return false
        } finally {
            return true
        }
    }

    public fun isShowing(): Boolean {
        val dialog = alertDialog
        if(dialog != null && dialog.isShowing())
            return true
        return false
    }

    fun dismissDialog() {
        alertDialog?.dismiss();
    }

    interface DialogClick {
        fun dialogYesClick(from: String);
        fun dialogNoClick(from: String);
    }
}