package com.oladipo.fairmoneytest.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.oladipo.fairmoneytest.model.Data
import com.oladipo.fairmoneytest.model.Detail
import com.oladipo.fairmoneytest.model.User

@Entity
data class DatabaseUsers(
    @PrimaryKey
    val id: String, val lastName: String, val firstName: String,
    val email:String, val title:String, val picture:String
)

@Entity
data class UserDetails(
    @PrimaryKey
    val id: String,
    val lastName: String,
    val phone: String,
    val dateOfBirth: String,
    val email: String,
    val location: String,
    val firstName: String,
    val gender: String,
    val picture: String,
    val registerDate: String,
    val title: String
)

data class Location(val state:String, val street: String, val city: String,
                    val timezone:String, val country:String)

fun List<DatabaseUsers>.asDomainModel(): List<Data>{
    return map {
        Data(
            id = it.id,
            title = it.title,
            lastName = it.lastName,
            firstName = it.firstName,
            email = it.email,
            picture = it.picture
        )
    }
}

//fun List<UserDetails>.asDomailModel():List<Detail>{
//    return map {
//        Detail(
//            id = it.id,
//            title = it.title,
//            lastName = it.lastName,
//            firstName = it.firstName,
//            email = it.email,
//            picture = it.picture,
//            gender = it.gender,
//            registerDate = it.registerDate,
//            dateOfBirth = it.dateOfBirth,
//            phone = it.phone
//        )
//    }
//}