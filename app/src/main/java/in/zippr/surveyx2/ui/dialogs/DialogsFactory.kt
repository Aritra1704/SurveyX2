package `in`.zippr.surveyx2.ui.dialogs

import android.os.Bundle
import java.util.ArrayList

class DialogsFactory {

    fun singleClickDialog(title: String, data: ArrayList<String>, isCancelable: Boolean): SingleClickDialog {
        val args = Bundle(3)
        args.putString(SingleClickDialog.ARG_TITLE, title)
        args.putStringArrayList(SingleClickDialog.ARG_DATA, data)
        args.putBoolean(SingleClickDialog.ARG_ISCANCEL, isCancelable)

        val singleClickDialog = SingleClickDialog()
        singleClickDialog.setArguments(args)

        return singleClickDialog
    }

    fun customDialog(strTitle: String,
                     strMessage: String,
                     positiveBtnName: String,
                     negativeBtnName: String,
                     neutralBtnName: String,
                     from: String,
                     isCancelable: Boolean,
                     dialogClick: CustomDialog.DialogClick): CustomDialog {

        return CustomDialog(strTitle, strMessage, positiveBtnName, negativeBtnName, neutralBtnName, from, isCancelable, dialogClick)
    }

    fun progressDialog(strTitle: String, strMessage: String, isCancelable: Boolean, isCustom: Boolean): ProgressDialog {

        return ProgressDialog(strTitle, strMessage, isCancelable, isCustom)
    }

    fun progressDialog(strTitle: String, strMessage: String, progress: Int, isCancelable: Boolean, isCustom: Boolean): ProgressDialog {

        return ProgressDialog(strTitle, strMessage, progress, isCancelable, isCustom)
    }

    fun progressDialog(isCancelable: Boolean): ProgressDialog {

        return ProgressDialog(isCancelable)
    }
}