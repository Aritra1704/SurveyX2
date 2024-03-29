package `in`.zippr.surveyx2.ui.dialogs

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDialogFragment
import java.util.ArrayList

/**
 * Single click selection
 */
class SingleClickDialog : BaseDialog() {

    companion object {

        val ARG_TITLE = "ARG_TITLE"
        val ARG_DATA = "ARG_DATA"
        val ARG_ISCANCEL = "ARG_ISCANCEL"

        fun newInstance(title: String, data: ArrayList<String>): SingleClickDialog {
            val args = Bundle()
            args.putSerializable("title", title)
            args.putStringArrayList("data", data)
            val fragment = SingleClickDialog()
            fragment.arguments = args
            return fragment
        }
    }

    internal var mListener: OnSelectedListener? = null

    fun setOnSelectedListener(listener: OnSelectedListener) {
        mListener = listener
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val title = arguments!!.getString(ARG_TITLE)
        val data = arguments!!.getStringArrayList(ARG_DATA)
        val isCancel = arguments!!.getBoolean(ARG_ISCANCEL)

        val charSequences = arrayOfNulls<CharSequence>(data!!.size)
        for (i in data.indices) {
            charSequences[i] = data[i]
        }

        return AlertDialog.Builder(activity!!)
            .setTitle(title)
            .setCancelable(isCancel)
            .setItems(charSequences) { dialog, which -> mListener?.onSelect(which) }
            .create()
    }
}

interface OnSelectedListener {
    fun onSelect(position: Int)
}