package com.example.androidarchitecturecomponent.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.androidarchitecturecomponent.data.User
import com.example.androidarchitecturecomponent.repository.Repository

class UserViewModel : AndroidViewModel(Application()) {
    //vars

    val repository: Repository = Repository(getApplication())

    fun getUsers(): LiveData<List<User>> {
        return  repository.allUsers()
    }
}