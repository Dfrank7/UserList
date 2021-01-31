package com.oladipo.fairmoneytest.Repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.oladipo.fairmoneytest.Utils
import com.oladipo.fairmoneytest.db.UserDb
import com.oladipo.fairmoneytest.db.asDomainModel
import com.oladipo.fairmoneytest.model.Data
import com.oladipo.fairmoneytest.model.NetworkUserContainer
import com.oladipo.fairmoneytest.model.asDatabaseModel
import com.oladipo.fairmoneytest.network.ApiData
import com.oladipo.fairmoneytest.network.Client
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repository(private val database: UserDb, private val context: Context) {

    val api = Client.getRetrofit()!!.create(ApiData::class.java)

    val users : LiveData<List<Data>> = Transformations.map(database.getUserDb().getUsers()){
        it.asDomainModel()
    }

    suspend fun refreshUsers(apikey: String) {

            withContext(Dispatchers.IO) {
                Log.d("ok", "I made it her")
                val result = api.getUsers(apikey)
                Log.d("ok", "I made it hereeee")
                val data = result.data
                val dataContainer = NetworkUserContainer(data)
                database.getUserDb().insertUsers(*dataContainer.asDatabaseModel())
            }

        }
    }
