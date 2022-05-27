package com.example.ticketgoapp.models

import android.util.Log

object UserToken {

    private var userToken: String? = ""

    fun getToken(): String {
        return userToken!!
    }

    fun setToken(token: String) {
        userToken = token
    }
}