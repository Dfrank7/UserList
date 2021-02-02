package com.oladipo.fairmoneytest.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {

    @Query("select * from DatabaseUsers")
    fun getUsers(): LiveData<List<DatabaseUsers>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUsers(vararg users: DatabaseUsers )

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserDetail(vararg details: UserDetails)

    @Query("select * from UserDetails where id = :id")
    fun getUserDetail(id: String): LiveData<UserDetails>
}