package com.oladipo.fairmoneytest.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

abstract class UserDb : RoomDatabase(){
    abstract fun getUserDb(): UserDao

    companion object {
        @Volatile
        var INSTANCE: UserDb? = null

        fun getInstance(context: Context): UserDb {

            val tempInstance = INSTANCE

            if (tempInstance != null) {

                return tempInstance
            }

            synchronized(this) {

                val instance = Room.databaseBuilder(context.applicationContext,
                    UserDb::class.java, "users_db").build()

                INSTANCE = instance

                return instance
            }
        }


    }
}