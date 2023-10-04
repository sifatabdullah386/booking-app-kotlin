package com.example.bookingapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class Users(
    @PrimaryKey(autoGenerate = true) val itemId: Int? = 0,
    @ColumnInfo(name = "id") val id: String?,
    @ColumnInfo(name = "hotel_id") val hotelId: String?,
    @ColumnInfo(name = "access_token") val accessToken: String?,
    @ColumnInfo(name = "area") val area: String?,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "phone") val phone: String?,
    @ColumnInfo(name = "email") val email: String?,
    @ColumnInfo(name = "user_name") val userName: String?,
    @ColumnInfo(name = "address") val address: String?,
    @ColumnInfo(name = "zipcode") val zipcode: String?,
    @ColumnInfo(name = "image") val image: String?
)