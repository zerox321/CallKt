package com.eslam.callkt.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(
    entities = [
        ClientEntity::class

    ],
    version = 1,
    exportSchema = false
)
abstract class RoomDB : RoomDatabase() {

    abstract val dao: MyDao


    companion object {
        private const val dbName = "com.eslam.callkt.room"
        @Volatile
        private var INSTANCE: RoomDB? = null

        fun getInstance(context: Context): RoomDB {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context,
                        RoomDB::class.java,
                        dbName
                    )
                        .fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}
