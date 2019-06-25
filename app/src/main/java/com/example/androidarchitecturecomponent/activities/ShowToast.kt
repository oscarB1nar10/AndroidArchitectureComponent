package com.example.androidarchitecturecomponent.activities

import android.content.Context
import android.widget.Toast

class ShowToast(val message: String, val context: Context){
    init {
        Toast.makeText(context,message,Toast.LENGTH_LONG).show()
    }
}