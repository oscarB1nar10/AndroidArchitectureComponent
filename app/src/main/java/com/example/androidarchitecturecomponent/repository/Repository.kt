package com.example.androidarchitecturecomponent.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.androidarchitecturecomponent.data.AppDatabase
import com.example.androidarchitecturecomponent.data.User
import com.example.androidarchitecturecomponent.data.UserDao

class Repository(val application: Application){
    //vars
    private val appDB: AppDatabase?
    private val userDao: UserDao?
    private var mutableLiveData: MutableLiveData<List<User>> = MutableLiveData()
    //initializer block
    init {
        appDB = AppDatabase.getInstance(application)
        userDao = appDB.userDao()
        allUsers()
    }

    fun allUsers():LiveData<List<User>>{
        mutableLiveData = userDao!!.getAll()
        return mutableLiveData
    }

    fun addUser(user: User){
        userDao!!.insert(user)
    }

}