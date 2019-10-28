package `in`.zippr.surveyx2.common

import `in`.zippr.surveyx2.R
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.preference.PreferenceManager

class AppPreference {
    private var preferences: SharedPreferences
    private var edit: SharedPreferences.Editor

    constructor(context: Context) {
        preferences	=	context.getSharedPreferences(context.getString(R.string.app_name), MODE_PRIVATE);
        edit = preferences.edit()
    }

    fun setDataPref(strKey: String, strValue: String) {
        edit.putString(strKey, strValue)
        commitPreference()
    }

    fun removeFromPreference(strKey: String) {
        edit.remove(strKey)
    }

    fun commitPreference() {
        edit.commit()
    }

    fun getDataPref(strKey: String): String? {
        return preferences.getString(strKey, "")
    }

    fun getDataPref(strKey: String, defaultValue: String): String? {
        return preferences.getString(strKey, defaultValue)
    }

    fun clear() {
        edit.clear()
        commitPreference()
    }
}