package com.oladipo.fairmoneytest.viemodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.oladipo.fairmoneytest.Repository.Repository
import com.oladipo.fairmoneytest.Utils
import com.oladipo.fairmoneytest.db.UserDb
import com.oladipo.fairmoneytest.model.Data
import kotlinx.coroutines.launch

class MainViewModel(application: Application): AndroidViewModel(application){

    private val database = UserDb.getInstance(application)

    private val repository = Repository(database, application.applicationContext)

    private val _status = MutableLiveData<DummyAPIStatus>()
    val status: LiveData<DummyAPIStatus> get() = _status

    private val _navigateToDetails = MutableLiveData<Data>()
    val navigateToDetails: LiveData<Data>
        get() = _navigateToDetails
    private val _checkInternet = MutableLiveData<Boolean>()
    val checkInternet : LiveData<Boolean>
        get() = _checkInternet

    init {
        viewModelScope.launch {
            try {
                _status.value = DummyAPIStatus.LOADING
                repository.refreshUsers("601507df72565d3b80738ab4")
            }catch (e:Exception){
                //e.printStackTrace()
                _status.value = DummyAPIStatus.ERROR
            }finally {
                _status.value = DummyAPIStatus.DONE
            }
        }

        viewModelScope.launch {
            _checkInternet.value = Utils.isConnectionAvailable(application.applicationContext)
        }
    }

    val users = repository.users

    fun onUserClicked(user: Data) {
        _navigateToDetails.value = user
    }

    fun onUserCompleteNavigation(){
        _navigateToDetails.value = null
    }



    enum class DummyAPIStatus {
        LOADING,
        ERROR,
        DONE
    }

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
