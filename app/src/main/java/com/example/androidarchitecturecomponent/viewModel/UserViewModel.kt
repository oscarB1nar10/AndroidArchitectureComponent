package com.example.androidarchitecturecomponent.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.androidarchitecturecomponent.data.User
import com.example.androidarchitecturecomponent.repository.Repository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class UserViewModel(application : Application) : AndroidViewModel(application) {
    //vars
    var context:Application? = null
    var repository:Repository? = null
    init {
        context = getApplication()//get Context necessary to create data base instance
        repository = Repository(context!!)
    }

    /**
     * This method allow us to retrieve all user saved on Room from ViewModel
     * because this may take long time and could block the main thread so i  solve use coroutines to run operation in other thread
     * and return the value when all ok.(async)
     */
    suspend fun getUsers(): LiveData<List<User>> {

        val deferred = GlobalScope.async {
            repository!!.allUsers()
        }
        return deferred.await()
    }

    /**
     * This method allows save a specific user on Room from ViewModel
     */
    fun addUser(user: User){
        repository!!.addUser(user)
    }
}