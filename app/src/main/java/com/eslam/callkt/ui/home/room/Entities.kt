package com.eslam.callkt.ui.home.room

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "client_table")
data class ClientEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String?,
    val notes: String?,
    val mobile: String?,
    val email: String?,
    val avatar: String?
)

