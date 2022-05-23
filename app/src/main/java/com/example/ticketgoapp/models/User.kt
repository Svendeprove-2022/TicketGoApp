package com.example.ticketgoapp.models

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required
import org.bson.types.ObjectId;

open class User(
    @PrimaryKey var _id: ObjectId? = null,

    var _partition: String? = null,

    var address: String? = null,

    var city: String? = null,

    var country: String? = null,

    var email: String? = null,

    var first_name: String? = null,

    var last_name: String? = null,

    var mobile: String? = null,

    @Required
    var role: RealmList<String> = RealmList(),

    var zip_code: String? = null
) : RealmObject()

