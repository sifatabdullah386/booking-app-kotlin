package com.example.bookingapp.repositories

import com.example.bookingapp.api.MyAPIServices
import com.example.bookingapp.api.RetrofitClientInstance
import com.example.bookingapp.model.RoomItems

class GetRoomList {

    suspend fun fetchData(): ArrayList<RoomItems>? {
        return try {
            val myAPIService = RetrofitClientInstance.getRetrofitInstance()!!.create(MyAPIServices::class.java)
            val response = myAPIService.getRoomList("")
            if (response.isSuccessful) {
                response.body()
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }
}