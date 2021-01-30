package com.oladipo.fairmoneytest.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
interface UserDao {

    @Query("select * from DatabaseUsers")
    fun getUsers(): LiveData<List<DatabaseUsers>>
}