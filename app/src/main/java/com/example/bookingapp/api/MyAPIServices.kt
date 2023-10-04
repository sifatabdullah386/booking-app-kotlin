package com.example.bookingapp.api

import com.example.bookingapp.model.RoomItems
import com.google.gson.JsonObject
import okhttp3.RequestBody
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface MyAPIServices {

    @POST("auth/login")
    fun createLogin(@Body body: JsonObject): Call<JsonObject>

    @GET("rooms_ratings/roominfolist?is_dep=1")
    suspend fun getRoomList(@Header("Authorization") accessToken: String?): Response<ArrayList<RoomItems>>
}