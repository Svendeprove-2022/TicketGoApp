package com.example.ticketgoapp.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apollographql.apollo3.api.ApolloResponse
import com.example.ticketgoapp.UpdateOneUserMutation
import com.example.ticketgoapp.apollo.apolloClient
import com.example.ticketgoapp.models.User
import com.example.ticketgoapp.models.UserToken
import com.example.ticketgoapp.realm.ticketGoApp
import io.realm.mongodb.App
import io.realm.mongodb.Credentials
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.bson.types.ObjectId

class SignUpViewModel : ViewModel() {

    private var user: User = User()
    private lateinit var password: String

    val registerResponse: MutableLiveData<App.Result<Void>> = MutableLiveData()
    val loginResponse: MutableLiveData<App.Result<io.realm.mongodb.User>> = MutableLiveData()
    val updateResponse: MutableLiveData<ApolloResponse<UpdateOneUserMutation.Data>> =
        MutableLiveData()

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
    }

    fun registerUser() {
        ticketGoApp.emailPassword.registerUserAsync(user.email!!, password) {
            registerResponse.postValue(it)
        }
    }

    fun logInUser() {
        val creds = Credentials.emailPassword(user.email!!, password)
        Log.d("createflow", user.email.toString())
        Log.d("createflow", password)

        ticketGoApp.loginAsync(creds) {
            Log.d("createflow login", it.toString())
            if (it.isSuccess) {
                UserToken.setToken(ticketGoApp.currentUser()!!.accessToken)
                user._id = ObjectId(ticketGoApp.currentUser()!!.id)
            }
            loginResponse.postValue(it)
        }
    }

    fun updateUser() {
        viewModelScope.launch(Dispatchers.IO) {
            val mutationData = UpdateOneUserMutation(
                user._id.toString(),
                user.address.toString(),
                user.city.toString(),
                user.country.toString(),
                user.first_name.toString(),
                user.last_name.toString(),
                user.mobile.toString(),
                user.zip_code.toString()
            )

            val response = apolloClient().mutation(mutationData).execute()
            updateResponse.postValue(response)
            logoutUser()
        }
    }

    private fun logoutUser() {
        ticketGoApp.currentUser()!!.logOut()
    }

}