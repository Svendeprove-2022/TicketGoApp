package com.example.ticketgoapp.models

object UserToken {

    private var userToken: String? = ""

    fun getToken(): String {
        return userToken!!
    }

    fun setToken(token: String) {
        userToken = token
    }
}