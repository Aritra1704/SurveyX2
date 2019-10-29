package `in`.zippr.surveyx2.ui.dialogs

import `in`.zippr.surveyx2.R
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.os.Handler
import android.text.TextUtils
import androidx.appcompat.app.AlertDialog
import org.jetbrains.anko.support.v4.alert

class CustomDialog(
    strTitle: String,
    strMessage: String,
    positiveBtnName: String,
    negativeBtnName: String,
    neutralBtnName: String,
    from: String,
    isCancelable: Boolean,
    dialogClick: DialogClick
) : BaseDialog() {

    private var strTitle: String? = null
    private var strMessage: String? = null
    private var positiveBtnName: String? = null
    private var negativeBtnName: String? = null
    private var neutralBtnName: String? = null
    private var from: String? = null
    private var isCancel = false
    private var alertDialog: AlertDialog? = null
    private var mContext: Context? = null
    private var dialogClick: DialogClick? = null

    init {
        try {
            mContext = activity
            this.strTitle = strTitle
            this.strMessage = strMessage
            this.positiveBtnName = positiveBtnName
            this.negativeBtnName = negativeBtnName
            this.neutralBtnName = neutralBtnName
            this.isCancel = isCancelable
            this.dialogClick = dialogClick
            if (!TextUtils.isEmpty(from)) this.from = from else this.from = ""
        } catch (e: Exception) {
//                Crashlytics.log(Log.ERROR, "Custom dialog", e.localizedMessage)
            e.printStackTrace()
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val builder = AlertDialog.Builder(activity!!, R.style.DialogTheme)
        builder.setTitle(strTitle)
        builder.setMessage(strMessage)
        if(!TextUtils.isEmpty(positiveBtnName))
            builder.setPositiveButton(positiveBtnName, DialogInterface.OnClickListener { dialog, which ->
                dialogClick?.let {
                    dialogClick!!.dialogYesClick(from.toString())
                }
                dialog.dismiss()
            })
        if(!TextUtils.isEmpty(negativeBtnName))
            builder!!.setNegativeButton(negativeBtnName, DialogInterface.OnClickListener { dialog, which ->
                dialogClick?.let {
                    dialogClick!!.dialogNoClick(from.toString())
                }
                dialog.dismiss()
            })
        if(!TextUtils.isEmpty(neutralBtnName))
            builder!!.setNeutralButton(neutralBtnName, DialogInterface.OnClickListener { dialog, which ->
                dialogClick?.let {
                    dialogClick!!.dialogNeutralClick(from.toString())
                }
                dialog.dismiss()
            })
        builder.setCancelable(isCancel)
        alertDialog = builder.create()

        return alertDialog!!
    }
//
//    private fun showCustomDialog() {
//        try {
//            var show = false
//
//            val context = mContext
//            if(context == null)
//                return
//
//            if (alertDialog == null) {
//                alertDialog = AlertDialog.Builder(context).create()
//                show = setDialog()
//            } else if (alertDialog != null) {
//                alertDialog?.dismiss()
//                show = setDialog()
//            } else
//                show = setDialog()
//            if (show)
//                Handler().post {
//                    alertDialog?.show()
//                }
//        } catch (e: Exception) {
////                Crashlytics.log(Log.ERROR, "Custom dialog", e.localizedMessage)
//            e.printStackTrace()
//        }
//
//    }
//
//    private fun setDialog(): Boolean {
//        try {
//            alertDialog?.setTitle(strTitle)
//            alertDialog?.setMessage(strMessage)
//            alertDialog?.setCanceledOnTouchOutside(isCancelable)
//            alertDialog?.setButton(
//                AlertDialog.BUTTON_POSITIVE, positiveBtnName,
//                DialogInterface.OnClickListener { dialog, which ->
//                    var click = dialogClick
//                    if(click != null) click.dialogYesClick(from.toString())
//                    dialog.dismiss()
//                })
//            alertDialog?.setButton(
//                AlertDialog.BUTTON_NEGATIVE, negativeBtnName,
//                DialogInterface.OnClickListener { dialog, which ->
//                    var click = dialogClick
//                    if(click != null) click.dialogNoClick(from.toString())
//                    dialog.dismiss()
//                })
//        } catch (ex: Exception) {
//            ex.printStackTrace()
//            return false
//        } finally {
//            return true
//        }
//    }

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
        fun dialogNeutralClick(from: String);
        fun dialogNoClick(from: String);
    }
}