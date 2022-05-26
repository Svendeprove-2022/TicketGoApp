package com.example.ticketgoapp.models

import android.util.Log

object UserToken {

    private var userToken: String? = ""

    fun getToken(): String {
        Log.d("usertoken get", userToken!!)
        return userToken!!
    }

    fun setToken(token: String) {
        Log.d("usertoken set", token)
        userToken = token
    }
}