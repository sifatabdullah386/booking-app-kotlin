package com.example.bookingapp.utilities

import android.content.Context
import android.content.SharedPreferences
import android.util.Log

class SessionManager(private var _context: Context) {
    // Shared Preferences
    private var pref: SharedPreferences
    private var editor: SharedPreferences.Editor

    // Shared pref mode
    private var PRIVATE_MODE = 0

    init {
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        editor = pref.edit()
    }

    fun setLogin(isLoggedIn: Boolean) {
        editor.putBoolean(KEY_IS_LOGGED_IN, isLoggedIn)

        // commit changes
        editor.commit()
        Log.d(TAG, "User session modified!")
    }

    val isLoggedIn: Boolean
        get() = pref.getBoolean(KEY_IS_LOGGED_IN, false)

    fun setValue(name: String?, sesValue: String?) {
        editor.putString(name, sesValue)

        // commit changes
        editor.commit()
    }

    fun getValue(name: String?): String? {
        return pref.getString(name, "")
    }

    fun setValueInt(name: String?, sesValue: Int) {
        editor.putInt(name, sesValue)

        // commit changes
        editor.commit()
    }

    fun getValueInt(name: String?): Int {
        return pref.getInt(name, 0)
    }

    fun ClearAll() {
        editor.clear()
    }

    companion object {
        // LogCat tag
        private val TAG = SessionManager::class.java.simpleName

        // Shared preferences file name
        private const val PREF_NAME = "BuildersAppLogin"
        private const val KEY_IS_LOGGED_IN = "isLoggedIn"
    }
}