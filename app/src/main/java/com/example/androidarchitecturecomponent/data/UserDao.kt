package com.example.androidarchitecturecomponent.data

import androidx.lifecycle.MutableLiveData
import androidx.room.*

@Dao
interface UserDao{
    @Query("SELECT * FROM user")
    fun getAll(): MutableLiveData<List<User>>

    @Update
    fun updateUsers(vararg users: User)

    @Insert
    fun insert(vararg user: User)

    @Delete
    fun delete(user: User)
}