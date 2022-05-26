package com.example.ticketgoapp.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apollographql.apollo3.exception.ApolloException
import com.example.ticketgoapp.GetUsersQuery
import com.example.ticketgoapp.apolloClient
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    fun getData() {
        viewModelScope.launch {
            try {
                val response = apolloClient().query(GetUsersQuery()).execute()
                Log.d("reponse", response.data?.users.toString())
            } catch (e: ApolloException) {
                Log.d("apollo get", "exception: $e.")
            }
        }
    }

}