package `in`.zippr.surveyx2.ui.dialogs

import `in`.zippr.surveyx2.R
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.widget.TextView

internal class ProgressDialog : BaseRunnable {
    private var strTitle: String? = null
    private var strMessage: String? = null
    private var isCancelable = false
    private var progress: Int = 0
    private var mContext: Context? = null
    private var dialogLoader: Dialog? = null

    val dialogView: View by lazy {
        LayoutInflater.from(mContext).inflate(R.layout.custom_loader, null)
    }

    constructor(context : Context, isCancelable: Boolean) {
        this.mContext = context
        this.strTitle = null
        this.strMessage = null
        this.progress = 0
        this.isCancelable = isCancelable
    }

    constructor(context : Context, strTitle: String, strMessage: String, isCancelable: Boolean) {
        this.mContext = context
        this.strTitle = strTitle
        this.strMessage = strMessage
        this.progress = 0
        this.isCancelable = isCancelable
    }

    constructor(context : Context, strTitle: String, strMessage: String, progress: Int, isCancelable: Boolean) {
        this.mContext = context
        this.strTitle = strTitle
        this.strMessage = strMessage
        this.progress = progress
        this.isCancelable = isCancelable
    }

    val tvLoadHead: TextView by lazy {
        dialogView.findViewById<TextView>(R.id.tvLoadHead)
    }

    val tvLoadMsg: TextView by lazy {
        dialogView.findViewById<TextView>(R.id.tvLoadMsg)
    }

    override fun run() {
        try {
            var show = false
            val view = LayoutInflater.from(mContext).inflate(R.layout.custom_loader, null)

            val dialog = dialogLoader
            if (dialogLoader == null) {
                dialogLoader = Dialog(mContext!!)
                dialogLoader?.requestWindowFeature(Window.FEATURE_NO_TITLE)
                dialogLoader?.setContentView(view)
                dialogLoader?.getWindow()!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                show = true
            } else if (dialogLoader != null) {
//                    val dialog = dialogLoader
//                    if(dialog.isShowing())
                dialogLoader?.dismiss()
                show = true
            } else {
                Log.i("showLoader", "none")
            }

            if (show) {
                dialog?.setCancelable(isCancelable)

//                    val tvLoadHead = dialogLoader.findViewById(R.id.tvLoadHead)
//                    val tvLoadMsg = dialogLoader.findViewById(R.id.tvLoadMsg)
//
                val context = mContext
                var loading: String = ""
                if(context != null)
                    loading = context.getString(R.string.loading)

                if (!TextUtils.isEmpty(strTitle))
                    tvLoadHead.setText(strTitle)
                else
                    tvLoadHead.setText(loading)

                if (!TextUtils.isEmpty(strMessage)) {
                    tvLoadMsg.setText(strMessage)
                    tvLoadMsg.setVisibility(View.VISIBLE)
                } else
                    tvLoadMsg.setVisibility(View.GONE)

//                tvLoadHead.setTypeface(tfBold, Typeface.BOLD)
//                tvLoadMsg.setTypeface(tfRegular, Typeface.NORMAL)
                dialog?.show()
            }

        } catch (e: Exception) {
            e.printStackTrace()
            dialogLoader = null
        }
    }

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