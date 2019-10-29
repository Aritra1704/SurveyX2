package `in`.zippr.surveyx2.ui.dialogs

import `in`.zippr.surveyx2.R
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import org.jetbrains.anko.find

class ProgressDialog : BaseDialog {
    private var strTitle: String? = null
    private var strMessage: String? = null
    private var isCancel = false
    private var isCustom = false
    private var progress: Int = 0
    private var mContext: Context? = null
    private var dialogLoader: Dialog? = null

    val dialogView: View by lazy {
        LayoutInflater.from(mContext).inflate(R.layout.custom_loader, null)
    }

    constructor(isCancelable: Boolean) {
        this.mContext = context
        this.strTitle = null
        this.strMessage = null
        this.progress = 0
        this.isCancel = isCancelable
    }

    constructor(strTitle: String, strMessage: String, isCancelable: Boolean, isCustom: Boolean) {
        this.mContext = context
        this.strTitle = strTitle
        this.strMessage = strMessage
        this.progress = 0
        this.isCancel = isCancelable
        this.isCustom = isCustom
    }

    constructor(strTitle: String, strMessage: String, progress: Int, isCancelable: Boolean, isCustom: Boolean) {
        this.mContext = context
        this.strTitle = strTitle
        this.strMessage = strMessage
        this.progress = progress
        this.isCancel = isCancelable
        this.isCustom = isCustom
    }

    val tvLoadHead: TextView by lazy {
        dialogView.findViewById<TextView>(R.id.tvLoadHead)
    }

    val tvLoadMsg: TextView by lazy {
        dialogView.findViewById<TextView>(R.id.tvLoadMsg)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity!!)
        if(isCustom) {
            val view = LayoutInflater.from(mContext).inflate(R.layout.custom_loader, null)

            if (TextUtils.isEmpty(strTitle))
                strTitle = getString(R.string.please_wait)
            if(TextUtils.isEmpty(strMessage))
                strMessage = getString(R.string.loading)

            tvLoadHead.text = strTitle
            tvLoadMsg.text = strMessage
            builder.setCustomTitle(view)
        } else {
            strTitle?.let {
                builder.setTitle(it)
            }
            strMessage?.let {
                builder.setMessage(strMessage)
            }
        }
        builder.setCancelable(isCancel)

        dialogLoader = builder.create()
        return dialogLoader!!
    }
//    override fun run() {
//        try {
//            var show = false
//            val view = LayoutInflater.from(mContext).inflate(R.layout.custom_loader, null)
//
//            val dialog = dialogLoader
//            if (dialogLoader == null) {
//                dialogLoader = Dialog(mContext!!)
//                dialogLoader?.requestWindowFeature(Window.FEATURE_NO_TITLE)
//                dialogLoader?.setContentView(view)
//                dialogLoader?.getWindow()!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//                show = true
//            } else if (dialogLoader != null) {
////                    val dialog = dialogLoader
////                    if(dialog.isShowing())
//                dialogLoader?.dismiss()
//                show = true
//            } else {
//                Log.i("showLoader", "none")
//            }
//
//            if (show) {
//                dialog?.setCancelable(isCancelable)
//
////                    val tvLoadHead = dialogLoader.findViewById(R.id.tvLoadHead)
////                    val tvLoadMsg = dialogLoader.findViewById(R.id.tvLoadMsg)
////
//                val context = mContext
//                var loading: String = ""
//                if(context != null)
//                    loading = context.getString(R.string.loading)
//
//                if (!TextUtils.isEmpty(strTitle))
//                    tvLoadHead.setText(strTitle)
//                else
//                    tvLoadHead.setText(loading)
//
//                if (!TextUtils.isEmpty(strMessage)) {
//                    tvLoadMsg.setText(strMessage)
//                    tvLoadMsg.setVisibility(View.VISIBLE)
//                } else
//                    tvLoadMsg.setVisibility(View.GONE)
//
////                tvLoadHead.setTypeface(tfBold, Typeface.BOLD)
////                tvLoadMsg.setTypeface(tfRegular, Typeface.NORMAL)
//                dialog?.show()
//            }
//
//        } catch (e: Exception) {
//            e.printStackTrace()
//            dialogLoader = null
//        }
//    }

    public fun isShowing(): Boolean {
        val dialog = dialogLoader
        if(dialog != null && dialog.isShowing())
            return true
        return false
    }

    fun dismissDialog() {
        dialogLoader?.dismiss();
    }
}