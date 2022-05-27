package com.example.ticketgoapp.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.exception.ApolloException
import com.example.ticketgoapp.GetDataFromOneUserQuery
import com.example.ticketgoapp.apollo.apolloClient
import com.example.ticketgoapp.models.User
import com.example.ticketgoapp.realm.ticketGoApp
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {

    private var user: User = User()

    fun getUserData(): LiveData<ApolloResponse<GetDataFromOneUserQuery.Data>> {
        val response = MutableLiveData<ApolloResponse<GetDataFromOneUserQuery.Data>>()

        viewModelScope.launch {
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
}