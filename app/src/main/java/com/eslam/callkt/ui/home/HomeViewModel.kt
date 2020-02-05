package com.eslam.callkt.ui.home

import android.app.Application
import android.content.Context
import android.util.Log
import android.view.View
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.eslam.callkt.BuildConfig
import com.eslam.callkt.internet.RetrofitAPI
import com.eslam.callkt.ui.home.HomeActivityFragmentDirections.actionHomeActivityFragmentToDetailFragment
import com.eslam.callkt.ui.home.room.ClientEntity
import com.eslam.callkt.ui.home.room.MyDao
import com.eslam.callkt.util.Pref.Companion.setStr
import com.eslam.callkt.util.navigate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class HomeViewModel(
    private val app: Application, private val dao: MyDao
) : AndroidViewModel(app), ClientsAdapter.ClickListener {
    override fun onRowClick(v: View, client: ClientEntity) {
        navigate(v, actionHomeActivityFragmentToDetailFragment())

    }

    private val tag = HomeViewModel::class.java.simpleName
    val adapter = ClientsAdapter(this)
    val isLoading: ObservableBoolean = ObservableBoolean()
    val clientsList = dao.getClientsList()

    init {
        getData(app.baseContext)
    }


    fun onRefresh() {
        getData(app.baseContext)

    }

    private fun getData(baseContext: Context) {
        isLoading.set(true)

        Log.e(tag, "getData  Start")
        viewModelScope.launch {
            try {

                val response = getConfigTask()

                Log.e(tag, "getData type  ${response.type}")

                if (response.type == "success")
                    setStr(baseContext, "offline", response.data?.offline!!)


                val clientListResponse = getClientsList()
                if (clientListResponse.type == "success")
                    saveClientsInDb(clientListResponse.data!!)
            } catch (ex: Throwable) {
                Log.e(tag, "getData ex  ", ex)
            }

        }
        isLoading.set(false)

    }

    private suspend fun saveClientsInDb(clients: List<ClientListResponseData?>) {
        withContext(Dispatchers.IO) {
            Log.e(tag, "saveClientsInDb  ${clients.size}")

            val data: List<ClientEntity> = clients.map {
                ClientEntity(
                    id = it!!.id!!,
                    name = it.name,
                    notes = it.notes,
                    mobile = it.mobile,
                    email = it.email,
                    avatar = it.avatar
                )
            }

            dao.insertClients(data)
            Log.e(tag, "saveClientsInDb  insertClients")

        }
    }


    private suspend fun getConfigTask() =
        withContext(Dispatchers.IO) {
            RetrofitAPI.getClient(BuildConfig.BaseLink).getConfigAsync(
                "get",
                "checkLinkType"
            ).await()
        }

    private suspend fun getClientsList() =
        withContext(Dispatchers.IO) {
            RetrofitAPI.getClient(BuildConfig.BaseLink).getClientsAsync(
                "getClients"
            ).await()
        }
}
