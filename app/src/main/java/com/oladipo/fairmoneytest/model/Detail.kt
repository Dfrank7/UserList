package com.oladipo.fairmoneytest.model

import androidx.room.Ignore

data class Detail(
    val id: String,
    val gender: String,
    val title: String,
    val lastName: String,
    val dateOfBirth: String,
    val firstName: String,
    val picture: String,
    val email: String,
    val registerDate: String,
    val location: Location,
    val phone: String,
    val locations: String = ""
)

data class Location(val state:String, val street: String, val city: String,
                    val timezone:String, val country:String)