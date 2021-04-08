package com.cendekia.githubapp.preference

import android.content.Context
import com.cendekia.githubapp.repositories.models.Remainder

class RemainderPreference(context: Context) {
    companion object {
        const val PREFS_NAME = "remainder_pref"
        private const val REMAINDER = "isRemaind"
    }

    private val preference = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun setRemainder(value: Remainder) {
        val editor = preference.edit()
        editor.putBoolean(REMAINDER, value.isRemainded)
        editor.apply()
    }

    fun getRemainder(): Remainder {
        val model = Remainder()
        model.isRemainded = preference.getBoolean(REMAINDER, false)
        return model
    }
}