package com.example.bookingapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class Users(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int?,
    @ColumnInfo(name = "hotel_id") val hotelId: Int?,
    @ColumnInfo(name = "access_token") val accessToken: String?,
    @ColumnInfo(name = "area") val area: Int?,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "phone") val phone: String?,
    @ColumnInfo(name = "email") val email: String?,
    @ColumnInfo(name = "user_name") val userName: String?,
    @ColumnInfo(name = "address") val address: String?,
    @ColumnInfo(name = "zipcode") val zipcode: String?,
    @ColumnInfo(name = "image") val image: String?
)

