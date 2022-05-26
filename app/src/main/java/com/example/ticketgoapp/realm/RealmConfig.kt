package com.example.ticketgoapp.realm

import android.app.Application
import android.content.ContentValues.TAG
import android.util.Log
import com.example.ticketgoapp.BuildConfig
import io.realm.Realm
import io.realm.mongodb.App
import io.realm.mongodb.AppConfiguration

lateinit var ticketGoApp: App

class RealmConfig : Application() {

    override fun onCreate() {
        super.onCreate()
        // Initialize the Realm SDK
        Realm.init(this)
        ticketGoApp = App(
            AppConfiguration.Builder(BuildConfig.MONGODB_REALM_APP_ID)
                .defaultSyncErrorHandler { _, error ->
                    Log.e(TAG, "Sync error: ${error.errorMessage}")
                }
                .build())
    }
}