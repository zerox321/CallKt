package com.eslam.callkt.ui.home

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.eslam.callkt.BuildConfig
import com.eslam.callkt.internet.RetrofitAPI
import com.eslam.callkt.util.Pref.Companion.setStr
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException


class HomeViewModel(app: Application) : AndroidViewModel(app) {
    private val tag = HomeViewModel::class.java.simpleName

    init {
        getConfig(app.baseContext)
    }

    private fun getConfig(baseContext: Context) {
        Log.e(tag, "getConfig  Start")
        viewModelScope.launch {
            try {

                val response = getConfigTask()
                Log.e(tag, "response.type   ${response.type}")

                if (response.type == "success")
                    setStr(baseContext, "offline", response.data?.offline!!)

            } catch (ex: IOException) {
                Log.e(tag, "ex  ", ex)


            }

        }
    }

    private suspend fun getConfigTask() =
        withContext(Dispatchers.IO) {

            RetrofitAPI.getClient(BuildConfig.BaseLink).getConfigAsync(
                "get",
                "checkLinkType"
            ).await()
        }
}
