package com.example.bookingapp.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = false)
data class RoomItems(
    @Json(name = "id")
    val id: Int,
    @Json(name = "hotel_name")
    val hotelName: String,
    @Json(name = "room_name")
    val roomName: String,
    @Json(name = "room_type")
    val roomType: String,
    @Json(name = "room_number")
    val roomNumber: String,
    @Json(name = "room_price")
    val price: Double,
    @Json(name = "discount_percentage")
    val discount: Double,
    @Json(name = "tax_percentage")
    val tax: Double,
    @Json(name = "room_image")
    val roomImage: String,
    @Json(name = "status")
    val status: String,
    @Json(name = "status_color")
    val statusColor: String,
)

@JsonClass(generateAdapter = false)
data class RoomImage(
    @Json(name = "msg")
    val msg: String,
    @Json(name = "uid")
    val uid: Int,
    @Json(name = "name")
    val name: String?,
    @Json(name = "status")
    val status: String,
    @Json(name = "url")
    val url: String
)