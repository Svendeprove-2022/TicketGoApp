package com.example.ticketgoapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.exception.ApolloException
import com.example.ticketgoapp.apollo.apolloClient
import com.example.ticketgoapp.realm.ticketGoApp
import kotlinx.coroutines.launch

class TicketsRecyclerViewViewModel : ViewModel() {

    fun getUserTickets(): LiveData<ApolloResponse<GetUsersTicketsQuery.Data>> {
        val response = MutableLiveData<ApolloResponse<GetUsersTicketsQuery.Data>>()

        viewModelScope.launch {
            try {
                response.postValue(
                    apolloClient().query(GetUsersTicketsQuery(ticketGoApp.currentUser()!!.id))
                        .execute()
                )
            } catch (e: ApolloException) {
                Log.d("apollo", "exception: $e")
            }
        }
        return response
    }

}