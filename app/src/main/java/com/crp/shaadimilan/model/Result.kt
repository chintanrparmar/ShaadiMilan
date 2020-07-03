package com.crp.shaadimilan.model


data class Result(
    val cell: String,
    val dob: Dob,
    val login: Login,
    val email: String,
    val gender: String,
    val location: Location,
    val name: Name,
    val nat: String,
    val phone: String,
    val picture: Picture
)