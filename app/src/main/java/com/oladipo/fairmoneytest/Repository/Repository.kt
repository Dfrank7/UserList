package com.oladipo.fairmoneytest.Repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.google.gson.Gson
import com.oladipo.fairmoneytest.helper.Utils
import com.oladipo.fairmoneytest.db.UserDb
import com.oladipo.fairmoneytest.db.UserDetails
import com.oladipo.fairmoneytest.db.asDomainModel
import com.oladipo.fairmoneytest.model.*
import com.oladipo.fairmoneytest.network.ApiData
import com.oladipo.fairmoneytest.network.Client
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repository(private val database: UserDb, private val context: Context) {

    val api = Client.getRetrofit()!!.create(ApiData::class.java)

    val users : LiveData<List<Data>> = Transformations.map(database.getUserDb().getUsers()){
        it.asDomainModel()
    }

    fun getUserDetail(id:String): LiveData<UserDetails>{
        return database.getUserDb().getUserDetail(id)
    }

    suspend fun refreshDetailUser(apikey: String, id: String){
        withContext(Dispatchers.IO){
            val result = api.getUserDetail(apikey, id)
            Log.d("okh234", Gson().toJson(result))
            val location = Gson().toJson(result.location)
            val newResult = Detail(id = result.id, lastName = result.lastName, phone = result.phone, dateOfBirth = result.dateOfBirth, email = result.email,
            locations = location, location = result.location, firstName = result.firstName, gender = result.gender, picture = result.picture,
                registerDate = result.registerDate, title = result.title)
            val dataContainer = NetworkUserDetailContainer(newResult)
            database.getUserDb().insertUserDetail(dataContainer.asDatabaseModel())
        }
    }

    suspend fun refreshUsers(apikey: String) {

        if (Utils.isConnectionAvailable(context)) {
            withContext(Dispatchers.IO) {
                val result = api.getUsers(apikey)
                val data = result.data
                val dataContainer = NetworkUserContainer(data)
                database.getUserDb().insertUsers(*dataContainer.asDatabaseModel())
            }
        }else{

        }

        }
    }
