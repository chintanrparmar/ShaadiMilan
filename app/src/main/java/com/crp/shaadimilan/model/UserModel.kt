package com.crp.shaadimilan.model

import androidx.annotation.Keep
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
@Keep
data class UserModel(
    @PrimaryKey
    val id: String,
    val age: String,
    val gender: String,
    val city: String,
    val name:String

)