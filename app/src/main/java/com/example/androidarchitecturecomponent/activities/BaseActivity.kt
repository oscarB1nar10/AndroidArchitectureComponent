package com.example.androidarchitecturecomponent.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity(val view : Int ) : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(view)
        initializeViews()
    }

    abstract fun initializeViews()


}