package com.eslam.callkt.ui.home

import android.util.Log
import com.eslam.callkt.BuildConfig
import com.eslam.callkt.internet.RetrofitAPI
import com.eslam.callkt.room.ClientEntity
import com.eslam.callkt.room.MyDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HomeRepo {

    private val tag = "HomeRepo : "

    suspend fun saveClientsInDb(dao: MyDao, clients: List<ClientListResponseData?>) {
        withContext(Dispatchers.IO) {

            Log.e(tag, "saveClientsInDb clients  ${clients.size}")

            val data: List<ClientEntity> = clients.map {
                ClientEntity(
                    id = it!!.id,
                    name = it.name,
                    notes = it.notes,
                    mobile = it.mobile,
                    email = it.email,
                    avatar = it.avatar
                )
            }
            Log.e(tag, "saveClientsInDb data  ${data.size}")

            dao.insertClients(data)
            Log.e(tag, "saveClientsInDb  insertClients")

        }
    }


    suspend fun getConfigTask() =
        withContext(Dispatchers.IO) {
            RetrofitAPI.getClient(BuildConfig.BaseLink).getConfigAsync(
                "get",
                "checkLinkType"
            ).await()
        }

    suspend fun getClientsList() =
        withContext(Dispatchers.IO) {
            RetrofitAPI.getClient(BuildConfig.BaseLink).getClientsAsync(
                "getClients"
            ).await()
        }
}