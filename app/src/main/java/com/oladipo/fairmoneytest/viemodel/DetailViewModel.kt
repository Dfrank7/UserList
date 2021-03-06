package com.oladipo.fairmoneytest.viemodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.oladipo.fairmoneytest.Repository.Repository
import com.oladipo.fairmoneytest.db.UserDb
import com.oladipo.fairmoneytest.db.UserDetails
import kotlinx.coroutines.launch

class DetailViewModel(application: Application): AndroidViewModel(application) {

    private val database = UserDb.getInstance(application)

    private val repository = Repository(database, application.applicationContext)

    lateinit var userDetails: LiveData<UserDetails>

    private val _status = MutableLiveData<DummyAPIStatus>()
    val status: LiveData<DummyAPIStatus> get() = _status


//    init {
//        viewModelScope.launch {
//            try {
//                repository.
//            }
//        }
//    }

    fun getUserDetailsFromRemote(apikey:String, id:String){
        viewModelScope.launch {
            try {
                _status.value = DummyAPIStatus.LOADING
                repository.refreshDetailUser(apikey, id)
            }catch (e:Exception){
                e.printStackTrace()
                _status.value = DummyAPIStatus.ERROR
                Log.d("okkkkk", e.localizedMessage+e.message)
            }finally {
                _status.value = DummyAPIStatus.DONE
            }
        }
    }

    fun getUserDetailFromLocal(id: String): LiveData<UserDetails>{
        viewModelScope.launch {
            try {
                userDetails = repository.getUserDetail(id)
            }catch (e:Exception){
                e.printStackTrace()
                Log.d("okkkkk", e.localizedMessage+e.message)
            }
        }
        return userDetails
    }

    enum class DummyAPIStatus {
        LOADING,
        ERROR,
        DONE
    }

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