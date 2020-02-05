package com.eslam.callkt.util

import android.content.Context


class Pref {
    companion object {


        private const val PREFS_NAME = "com.eslam.callkt.util"

        fun setInt(context: Context, key: String, value: Int) {
            val sharedPref = context.getSharedPreferences(PREFS_NAME, 0)
            val editor = sharedPref.edit()
            editor.putInt(key, value)
            editor.apply()
        }

        fun getInt(context: Context, key: String): Int {
            val prefs = context.getSharedPreferences(PREFS_NAME, 0)
            return prefs.getInt(key, 0)
        }

        fun setStr(context: Context, key: String, value: String) {
            val sharedPref = context.getSharedPreferences(PREFS_NAME, 0)
            val editor = sharedPref.edit()
            editor.putString(key, value)
            editor.apply()
        }

        fun getStr(context: Context, key: String): String {
            val prefs = context.getSharedPreferences(PREFS_NAME, 0)
            return prefs.getString(key, "")!!
        }


        fun setBoolean(context: Context, key: String, value: Boolean) {
            val sharedPref = context.getSharedPreferences(PREFS_NAME, 0)
            val editor = sharedPref.edit()
            editor.putBoolean(key, value)
            editor.apply()
        }

        fun getBoolean(context: Context, key: String): Boolean {
            val prefs = context.getSharedPreferences(PREFS_NAME, 0)
            return prefs.getBoolean(key, false)
        }
    }
}