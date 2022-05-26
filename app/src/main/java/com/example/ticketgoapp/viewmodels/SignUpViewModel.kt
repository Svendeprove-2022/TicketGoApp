package com.example.ticketgoapp.viewmodels

import android.provider.Settings.Global.getString
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apollographql.apollo3.exception.ApolloException
import com.example.ticketgoapp.UpdateUserMutation
import com.example.ticketgoapp.apolloClient
import com.example.ticketgoapp.models.User
import com.example.ticketgoapp.models.UserToken
import com.example.ticketgoapp.ticketGoApp
import io.realm.mongodb.Credentials
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.bson.types.ObjectId

class SignUpViewModel : ViewModel() {

    private var user: User = User()
    private lateinit var password: String
    private lateinit var userLoggedIn: io.realm.mongodb.User

//    fun createUser() = runBlocking {
//        registerUser()
//
//        loginUser()
//    }

    fun updateUser() {
        viewModelScope.launch(Dispatchers.IO) {
            val mutationData = UpdateUserMutation(
                user._id.toString(),
                user.first_name.toString(),
                user.city.toString(),
                user.country.toString(),
                user.first_name.toString(),
                user.last_name.toString(),
                user.mobile.toString(),
                user.zip_code.toString()
            )

            Log.d("userid", user._id.toString())
            Log.d("mutation data", mutationData.userId.toString())
            Log.d("mutation data", mutationData.address.toString())
            Log.d("mutation data", mutationData.city.toString())
            Log.d("mutation data", mutationData.country.toString())
            Log.d("mutation data", mutationData.firstName.toString())
            Log.d("mutation data", mutationData.lastName.toString())
            Log.d("mutation data", mutationData.mobile.toString())
            Log.d("mutation data", mutationData.zip_code.toString())

            try {
                val response = apolloClient().mutation(mutationData).execute()
                Log.d("response", response.data?.updateOneUser.toString())
            } catch (e: ApolloException) {
                Log.d("apollo", "exception: $e")
            }
        }
    }

    fun save1(email: String, password: String, firstName: String, lastName: String) {
        user.email = email
        this.password = password
        user.first_name = firstName
        user.last_name = lastName
    }

    fun save2(phone: String, address: String, postcode: String, city: String, country: String) {
        user.mobile = phone
        user.address = address
        user.zip_code = postcode
        user.city = city
        user.country = country

        Log.d("realm", user.email.toString())
        Log.d("realm", user.first_name.toString())
        Log.d("realm", user.last_name.toString())
        Log.d("realm", user.mobile.toString())
        Log.d("realm", user.address.toString())
        Log.d("realm", user.zip_code.toString())
        Log.d("realm", user.city.toString())
        Log.d("realm", user.country.toString())
    }

    fun registerUser() {
        ticketGoApp.emailPassword.registerUserAsync(user.email!!, password) {
            if (it.isSuccess) {
                Log.d("realm", "Registered user")
            } else {
                Log.d("realm", "Register Error: ${it.error}")
            }
        }
    }

    fun loginUser() {
        val creds = Credentials.emailPassword(user.email!!, password)
        Log.d("realm email", user.email.toString())
        Log.d("realm password", password)
        ticketGoApp.loginAsync(creds) {
            if (it.isSuccess) {
                Log.d("realm", "Login Successful")
                UserToken.setToken(ticketGoApp.currentUser()!!.accessToken)
                user._id = ObjectId(ticketGoApp.currentUser()!!.id)
            } else {
                Log.d("realm", "Login Error: ${it.error}")
            }
        }
    }

}



