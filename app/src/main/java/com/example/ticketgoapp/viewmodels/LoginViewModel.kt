package com.example.ticketgoapp.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import io.realm.mongodb.App
import io.realm.mongodb.Credentials

class LoginViewModel : ViewModel() {


    private lateinit var user: io.realm.mongodb.User

    fun loginUser(app: App) {
        val creds = Credentials.emailPassword("test@test.com", "testtest")
        app.loginAsync(creds) {
            if (it.isSuccess) {
                Log.d("realm", "Succes")
                user = app.currentUser()!!
                Log.d("realm", user.id)
            } else {
                Log.d("realm", "Error: ${it.error}")
            }
        }
    }
}