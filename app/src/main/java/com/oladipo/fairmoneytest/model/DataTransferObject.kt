package com.oladipo.fairmoneytest.model

import com.oladipo.fairmoneytest.db.DatabaseUsers
import com.oladipo.fairmoneytest.db.UserDetails
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class NetworkUserContainer(val datas: List<Data>)

fun NetworkUserContainer.asDatabaseModel(): Array<DatabaseUsers>{
    return datas.map {
        DatabaseUsers(
            id = it.id,
            lastName = it.lastName,
            firstName = it.firstName,
            email = it.email,
            title = it.title,
            picture = it.picture
        )
    }.toTypedArray()
}

@JsonClass(generateAdapter = true)
data class NetworkUserDetailContainer(val detail: Detail)

fun NetworkUserDetailContainer.asDatabaseModel(): UserDetails{
    return UserDetails(
        id = detail.id,
        lastName = detail.lastName,
        firstName = detail.firstName,
        email = detail.email,
        title = detail.title,
        phone = detail.phone,
        location = detail.locations,
        dateOfBirth = detail.dateOfBirth,
        gender = detail.gender,
        registerDate = detail.registerDate,
        picture = detail.picture
    )
}