package com.oladipo.fairmoneytest.viemodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.oladipo.fairmoneytest.Repository.Repository
import com.oladipo.fairmoneytest.db.UserDb
import kotlinx.coroutines.launch

class MainViewModel(application: Application): AndroidViewModel(application){

    private val database = UserDb.getInstance(application)

    private val repository = Repository(database, application.applicationContext)

    init {
        viewModelScope.launch {
            try {
                Log.d("okkk", "I made it her")
                repository.refreshUsers("601507df72565d3b80738ab4")
            }catch (e:Exception){
                //e.printStackTrace()
            }finally {

            }
        }
    }

    val users = repository.users



    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MainViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}
