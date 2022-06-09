package com.example.ticketgoapp.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.exception.ApolloException
import com.example.ticketgoapp.GetDataFromOneUserQuery
import com.example.ticketgoapp.UpdateOneUserMutation
import com.example.ticketgoapp.apollo.apolloClient
import com.example.ticketgoapp.realm.ticketGoApp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {

    fun logOutUser() {
        viewModelScope.launch(Dispatchers.IO) {
            ticketGoApp.currentUser()!!.logOut()
        }
    }

    fun getUserData(): LiveData<ApolloResponse<GetDataFromOneUserQuery.Data>> {
        val response = MutableLiveData<ApolloResponse<GetDataFromOneUserQuery.Data>>()

        viewModelScope.launch(Dispatchers.IO) {
            try {
                response.postValue(
                    apolloClient().query(GetDataFromOneUserQuery(ticketGoApp.currentUser()!!.id))
                        .execute()
                )
            } catch (e: ApolloException) {
                Log.d("apollo", "exception: $e")
            }
        }
        return response
    }

    fun saveUserData(
        address: String,
        city: String,
        country: String,
        firstname: String,
        lastname: String,
        phonenumber: String,
        zipcode: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            val mutationData = UpdateOneUserMutation(
                ticketGoApp.currentUser()!!.id,
                address,
                city,
                country,
                firstname,
                lastname,
                phonenumber,
                zipcode
            )
            try {
                val response = apolloClient().mutation(mutationData).execute()
                Log.d("response", response.data?.updateOneUser.toString())
            } catch (e: ApolloException) {
                Log.d("apollo", "exception: $e")
            }
        }
    }
}