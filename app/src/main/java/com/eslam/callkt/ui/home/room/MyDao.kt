package com.eslam.callkt.ui.home.room

import androidx.room.*
import kotlinx.coroutines.flow.Flow


@Dao
interface MyDao {


    @Insert(entity = ClientEntity::class, onConflict = OnConflictStrategy.REPLACE)
    fun insertClients(visit: List<ClientEntity>)


    @Query("SELECT * FROM client_table")
    fun getClientsList(): Flow<List<ClientEntity>>

}