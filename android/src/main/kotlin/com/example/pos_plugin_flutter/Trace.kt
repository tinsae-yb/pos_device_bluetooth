package com.example.pos_plugin_flutter

import android.util.Log


object TRACE {
    var NEW_LINE = System.getProperty("line.separator")
    private const val AppName = "POS_SDK"
    private const val isTesting = true
    fun i(string: String?) {
        if (isTesting) {
            Log.i(AppName, string!!)
        }
    }

    fun w(string: String?) {
        if (isTesting) {
            Log.e(AppName, string!!)
        }
    }

    fun e(exception: Exception) {
        if (isTesting) {
            Log.e(AppName, exception.toString())
        }
    }

    fun d(string: String?) {
        if (isTesting) {
            Log.d(AppName, string!!)
        }
    }

    fun a(num: Int) {
        if (isTesting) {
            Log.d(AppName, Integer.toString(num))
        }
    }
}