package com.oladipo.fairmoneytest.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(val data:List<Data>, val total:Int, val page:Int, val limit:Int, val offset: Int): Parcelable

@Parcelize
data class Data(val id: String, val lastName: String, val firstName: String,
                val email:String, val title:String, val picture:String) : Parcelable