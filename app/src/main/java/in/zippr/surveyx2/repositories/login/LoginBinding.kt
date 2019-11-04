package `in`.zippr.surveyx2.repositories.login

import android.text.TextWatcher
import android.widget.EditText
import androidx.databinding.BindingAdapter

@BindingAdapter("textChangedListener")
fun bindUserIdTextWatcher(edtUserId: EditText, textWatcher: TextWatcher) {
    edtUserId.addTextChangedListener(textWatcher)
}