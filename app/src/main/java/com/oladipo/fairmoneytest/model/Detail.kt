package com.oladipo.fairmoneytest.model

import androidx.room.Ignore

data class Detail(
    val id: String,
    val lastName: String,
    val phone: String,
    val dateOfBirth: String,
    val email: String,
    val locations: String,
    val location: Location,
    val firstName: String,
    val gender: String,
    val picture: String,
    val registerDate: String,
    val title: String
)

data class Location(val state:String, val street: String, val city: String,
                    val timezone:String, val country:String)