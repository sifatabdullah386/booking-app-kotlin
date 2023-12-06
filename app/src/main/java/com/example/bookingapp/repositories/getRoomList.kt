package com.example.bookingapp.repositories

import android.util.Log
import com.example.bookingapp.api.MyAPIServices
import com.example.bookingapp.api.RetrofitClientInstance
import com.example.bookingapp.model.RoomItems
import com.example.bookingapp.utilities.SessionManager
import retrofit2.Call
import retrofit2.Response

class GetRoomList {
    suspend fun fetchData() {
        val myAPIService = RetrofitClientInstance.getRetrofitInstance()!!.create(MyAPIServices::class.java)
        val call = myAPIService.getRoomList("")
        call.enqueue(object : retrofit2.Callback<ArrayList<RoomItems>> {
            override fun onResponse(call: Call<ArrayList<RoomItems>>, response: Response<ArrayList<RoomItems>>) {
                Log.d("Url Responded", response.toString())
                Log.d("Body Responded", response.body().toString())
            }

            override fun onFailure(call: Call<ArrayList<RoomItems>>, t: Throwable) {
                Log.d("Error", t.message.toString())
            }
        })
    }
}