package com.example.ticketgoapp.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ticketgoapp.models.UserToken
import com.example.ticketgoapp.realm.ticketGoApp
import io.realm.mongodb.Credentials

class LoginViewModel : ViewModel() {

    val loginReponse: MutableLiveData<Boolean> = MutableLiveData()

    fun loginUser(email: String, password: String) {
        val creds = Credentials.emailPassword(email, password)
        ticketGoApp.loginAsync(creds) {
            if (it.isSuccess) {
                Log.d("realm", "Login Successful")
                UserToken.setToken(ticketGoApp.currentUser()!!.accessToken)
                loginReponse.postValue(it.isSuccess)
            } else {
                Log.d("realm", "Login Error: ${it.error}")
                loginReponse.postValue(it.isSuccess)
            }
        }
    }

}