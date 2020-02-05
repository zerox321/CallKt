package com.eslam.callkt.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface MyDao {


    @Insert(entity = ClientEntity::class, onConflict = OnConflictStrategy.REPLACE)
    fun insertClients(visit: List<ClientEntity>)


    @Query("SELECT * FROM client_table")
    fun getClientsList(): Flow<List<ClientEntity>>

    @Query("SELECT * FROM client_table WHERE id=:id")
    fun getClientData(id: Int): LiveData<ClientEntity>

}