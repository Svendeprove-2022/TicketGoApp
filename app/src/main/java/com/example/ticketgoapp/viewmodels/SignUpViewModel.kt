package com.example.ticketgoapp.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.ticketgoapp.models.User
import com.example.ticketgoapp.ticketGoApp
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.kotlin.where
import io.realm.mongodb.Credentials
import io.realm.mongodb.sync.SyncConfiguration
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.bson.types.ObjectId

class SignUpViewModel : ViewModel() {

    private var user: User = User()
    lateinit var password: String
    private lateinit var currentUser: io.realm.mongodb.User

    fun createUser() = runBlocking {

        runBlocking {
            registerUser()

//            loginUser()
        }

//        ticketGoApp.emailPassword.registerUserAsync(user.email!!, password) {
//            if (it.isSuccess) {
//                Log.d("realm", "Registered user")
//            } else {
//                Log.d("realm", "Register Error: ${it.error}")
//            }
//        }


//        val creds = Credentials.emailPassword(user.email, password)
//        ticketGoApp.loginAsync(creds) {
//            if (it.isSuccess) {
//                Log.d("realm", "Login Successful")
//                currentUser = ticketGoApp.currentUser()!!
//                Log.d("realm", currentUser.id)
//            } else {
//                Log.d("realm", "Login Error: ${it.error}")
//            }
//        }


//        val config =
//            RealmConfiguration.Builder()
//                .build()
//        val realm = Realm.getInstance(config)

//        val configuration = SyncConfiguration.Builder(user) // use the RealmConfiguration.Builder for more options
//        val realm = Realm.open()

//        val config = RealmConfiguration.Builder()
//            .allowWritesOnUiThread(true)
//            .build()
//        val realm = Realm.getInstance(config)


//        val listener = RealmObjectChangeListener { changedUser: User?,
//                                                   changeSet: ObjectChangeSet? ->
//            if (changeSet!!.isFieldChanged("first_name")) {
//                Log.i("realm", "first_name changed")
//            } else {
//                Log.i("realm", "nothing changed")
//
//            }
//        }


//        val config =
//            RealmConfiguration.Builder()
//                .allowQueriesOnUiThread(true)
//                .allowWritesOnUiThread(true)
//                .build()
//        val realm = Realm.getInstance(config)


//        realm.executeTransactionAsync { r ->
//            r.insert(user)
//
//
//        }


//        val config =
//            RealmConfiguration.Builder(app.currentUser()!!, "user=${app.currentUser()?.id}")
//                .build()
//        val realm = Realm.getInstance(config)
//
//        // start a write transaction
//        realm.executeTransactionAsync { transactionRealm: Realm ->

    }

    fun updateUser() {
//        val config: SyncConfiguration =
//            SyncConfiguration.Builder(ticketGoApp.currentUser()!!, "_partition").build()
//        val realm = Realm.getInstance(config)
//
//        realm.executeTransactionAsync { transactionRealm: Realm ->
//            val updateUser = transactionRealm.where(User::class.java)
//                .equalTo("_id", user._id).findFirst()
//
//
//
//            updateUser?.first_name = user.first_name
//            updateUser?.last_name = user.last_name
//            updateUser?.mobile = user.mobile
//            updateUser?.address = user.address
//            updateUser?.zip_code = user.zip_code
//            updateUser?.city = user.city
//            updateUser?.country = user.country
//        }


        Log.d("realm", "updateUser method")

        val config = RealmConfiguration.Builder()
            .allowWritesOnUiThread(true)
            .allowQueriesOnUiThread(true)
            .build()
        val realm = Realm.getInstance(config)

        realm.executeTransactionAsync {
//            it.copyToRealm(user)

            val item = it.where<User>().equalTo("_id", user._id).findFirst()
            Log.d("realm", item?._id.toString())
            Log.d("realm", item?.email.toString())
            Log.d("realm", item?.first_name.toString())
            item?.first_name = user.first_name
            Log.d("realm", item?.first_name.toString())
        }

        realm.close()
    }

    fun save1(email: String, password1: String, firstName: String, lastName: String) {
        user.first_name = firstName
        user.last_name = lastName
        user.email = email
        password = password1
    }

    fun save2(phone: String, address: String, postcode: String, city: String, country: String) {
        user._id = ObjectId()
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

//    private lateinit var userLoggedIn: io.realm.mongodb.User

    private fun loginUser() {
        val creds = Credentials.emailPassword(user.email.toString(), password)
        Log.d("realm email", user.email.toString())
        Log.d("realm password", password)
        ticketGoApp.loginAsync(creds) {
            if (it.isSuccess) {
                Log.d("realm", "Login Successful")
                currentUser = ticketGoApp.currentUser()!!
                Log.d("realm", currentUser.id)
            } else {
                Log.d("realm", "Login Error: ${it.error}")
            }
        }
    }

    private suspend fun registerUser() = coroutineScope {
        launch {
            ticketGoApp.emailPassword.registerUserAsync(user.email!!, password) {
                if (it.isSuccess) {
                    Log.d("realm", "Registered user")
                } else {
                    Log.d("realm", "Register Error: ${it.error}")
                }
            }

        }
    }

//        fun editUser() {
//
//
//            val config = SyncConfiguration.Builder(app.currentUser(), "user=${user.id}")
//                .build()
//            val realm = Realm.getInstance(config)
//
//            // start a write transaction
//            realm.executeTransactionAsync { transactionRealm: Realm ->
//                // get a frog from the database to update
//                val user = transactionRealm.where(com.example.ticketgoapp.models.User::class.java)
//                    .equalTo("_id", ObjectId(app.currentUser()?.id)).findFirst()
//                // change the frog's name
//                user?.email = "test1@test1.com"
//            } // when the transaction completes, the frog's name and species
//// are updated in the database
//        }
}
