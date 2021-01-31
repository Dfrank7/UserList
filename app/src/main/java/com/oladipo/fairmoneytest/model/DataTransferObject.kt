package com.oladipo.fairmoneytest.model

import com.oladipo.fairmoneytest.db.DatabaseUsers
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