package com.eslam.callkt.ui.home

import android.app.Application
import android.content.Context
import android.util.Log
import android.view.View
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.eslam.callkt.ui.home.HomeActivityFragmentDirections.actionHomeActivityFragmentToDetailFragment
import com.eslam.callkt.room.ClientEntity
import com.eslam.callkt.room.MyDao
import com.eslam.callkt.util.Pref.Companion.setStr
import com.eslam.callkt.util.navigate
import kotlinx.coroutines.launch


class HomeViewModel(
    private val app: Application, private val dao: MyDao
) : AndroidViewModel(app), ClientsAdapter.ClickListener {

    private val repo:HomeRepo= HomeRepo()

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

                val response = repo.getConfigTask()

                Log.e(tag, "getData type  ${response.type}")

                if (response.type == "success")
                    setStr(baseContext, "offline", response.data?.offline!!)


                val clientListResponse = repo.getClientsList()
                if (clientListResponse.type == "success")
                    repo.saveClientsInDb(dao,clientListResponse.data!!)
            } catch (ex: Throwable) {
                Log.e(tag, "getData ex  ", ex)
            }

        }
        isLoading.set(false)

    }


    override fun onRowClick(v: View, client: ClientEntity) {
        navigate(v, actionHomeActivityFragmentToDetailFragment(client.id, client.name))

    }

}
