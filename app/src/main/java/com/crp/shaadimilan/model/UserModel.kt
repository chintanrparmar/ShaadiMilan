package com.crp.shaadimilan.model

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
@Keep
data class UserModel(
    @PrimaryKey
    val id: String,
    val name: String,
    val profilePic: String,
    val age: String,
    val gender: String,
    val city: String,
    val country: String,
    var inviteStatus:Int //0 default, 1 accept, 2 declined

    )