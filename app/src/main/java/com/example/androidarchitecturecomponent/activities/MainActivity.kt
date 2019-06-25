package com.example.androidarchitecturecomponent.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import com.example.androidarchitecturecomponent.R
import com.example.androidarchitecturecomponent.data.User
import com.example.androidarchitecturecomponent.viewModel.UserViewModel
import kotlinx.coroutines.launch

class MainActivity : BaseActivity(R.layout.activity_main) {

    override fun initializeViews() {

        val model = ViewModelProviders.of(this).get(UserViewModel(application)::class.java)
        //user information retrieve from Room
        lifecycleScope.launch{
            model.getUsers().observe(this@MainActivity, Observer<List<User>>{users ->
                var usersInfo: String = ""
                for(user: User in users){
                    usersInfo += "${user.firstName} ${user.lastName} \n"
                }
                Toast.makeText(this@MainActivity, "users information: \n$usersInfo", Toast.LENGTH_LONG).show()
            })
        }
    }



}
