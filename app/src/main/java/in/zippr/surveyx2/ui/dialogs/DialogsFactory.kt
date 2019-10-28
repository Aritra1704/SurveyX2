package `in`.zippr.surveyx2.ui.dialogs

import android.os.Bundle
import java.util.ArrayList

class DialogsFactory {

    fun singleClickDialog(title: String, data: ArrayList<String>): SingleClickDialog {
        val args = Bundle(3)
        args.putString(SingleClickDialog.ARG_TITLE, title)
        args.putStringArrayList(SingleClickDialog.ARG_DATA, data)

        val singleClickDialog = SingleClickDialog()
        singleClickDialog.setArguments(args)

        return singleClickDialog
    }
}