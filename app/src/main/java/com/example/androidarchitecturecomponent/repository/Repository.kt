package com.example.androidarchitecturecomponent.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.androidarchitecturecomponent.data.AppDatabase
import com.example.androidarchitecturecomponent.data.User
import com.example.androidarchitecturecomponent.data.UserDao
import kotlinx.coroutines.*

class Repository(val application: Application){
    //vars
    private val appDB: AppDatabase?
    private val userDao: UserDao?
    //initializer block
    init {
        appDB = AppDatabase.getInstance(application)
           // AppDatabase.getInstance(application)//Data base init
        userDao = appDB.userDao()
        //GlobalScope.launch {
            allUsers()
        //}
    }

    /**
     * This method allow us to retrieve all user saved on Room
     */
    fun allUsers():LiveData<List<User>>{
        /*val deferred = GlobalScope.async {
             userDao!!.getAll()
        }
        return deferred.await()*/
        return   userDao!!.getAll()
    }


    /**
     * This method allows save a specific user on Room
     */
    fun addUser(user: User){
        GlobalScope.launch {
            userDao!!.insert(user)
        }
    }

}