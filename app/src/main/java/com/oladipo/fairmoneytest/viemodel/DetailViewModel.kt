package com.oladipo.fairmoneytest.viemodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.oladipo.fairmoneytest.Repository.Repository
import com.oladipo.fairmoneytest.db.UserDb

class DetailViewModel(application: Application): AndroidViewModel(application) {

    private val database = UserDb.getInstance(application)

    private val repository = Repository(database, application.applicationContext)

//    init {
//        viewModelScope.launch {
//            try {
//                repository.
//            }
//        }
//    }



    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return DetailViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}