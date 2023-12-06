package com.example.bookingapp.view.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.bookingapp.R
import com.example.bookingapp.api.MyAPIServices
import com.example.bookingapp.api.RetrofitClientInstance.getRetrofitInstance
import com.example.bookingapp.database.UserDatabase
import com.example.bookingapp.database.Users
import com.google.gson.JsonObject
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response

@Suppress("NAME_SHADOWING")
class LoginActivity : BaseActivity() {
    private lateinit var userDb: UserDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        userDb = UserDatabase.getDatabase(this)

        val btnLogin = findViewById<TextView>(R.id.btn_submit) as TextView
        val username = findViewById<EditText>(R.id.username) as EditText
        val password = findViewById<EditText>(R.id.password) as EditText

        btnLogin.setOnClickListener(View.OnClickListener {
            val user = username.text.toString().trim()
            val pass = password.text.toString().trim()
            if (validate(user, pass)) {
                accessToLogin(user, pass)
            } else {
                ShowAlert("Username and Password is required")
            }
        })
    }

    private fun validate(user: String, pass: String): Boolean {
        return user.isNotEmpty() && pass.isNotEmpty()
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun accessToLogin(user: String?, pass: String?) {
//        ShowLoading()
        val paramObject = JsonObject()
        paramObject.addProperty("userid", user)
        paramObject.addProperty("password", pass)

        Log.d("request_objects", paramObject.toString())

        val myAPIService = getRetrofitInstance()!!.create(MyAPIServices::class.java)
        val call = myAPIService.createLogin(paramObject)

        call.enqueue(object : retrofit2.Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                Log.d("Url Responded", response.toString())
                val bodyResponse: JsonObject? = response.body()
                val convertJSON: JSONObject = JSONObject(bodyResponse.toString())
                Log.d("Body Responded", convertJSON.toString())

                val status = convertJSON.getString("status")
                val message = convertJSON.getString("message")

                if (status.equals("200")) {
                    val token = convertJSON.getJSONObject("token")
                    val accessToken = token.getString("access_token")
                    Log.d("AccessToken", accessToken)
                    sessionManager!!.setValue("access_token", accessToken)
                    val userData = convertJSON.getJSONObject("user")
                    val id = userData.getInt("id")
                    val area = userData.getInt("area")
                    val hotelId = userData.getInt("hotel_id")
                    val name = userData.getString("name")
                    val phone = userData.getString("phone")
                    val email = userData.getString("email")
                    val userName = userData.getString("user_name")
                    val address = userData.getString("address")
                    val zipcode = userData.getString("zipcode")
                    val image = userData.getString("image")
//                  sessionManager!!.setValue("name", userData.getString("name"))
                    sessionManager!!.setLogin(true)

                    GlobalScope.launch(Dispatchers.IO) {

                        val user = Users(
                            id,
                            hotelId,
                            accessToken,
                            area,
                            name,
                            phone,
                            email,
                            userName,
                            address,
                            zipcode,
                            image
                        )

                        userDb.userDao().deleteAllUsers()

//                        if (userDb.userDao().getAll().isNotEmpty()) {
                        userDb.userDao().insertAll(user)
//                        } else {
//                            userDb.userDao().deleteAllUsers()
//                        }

                        // Retrieve users
                        val users = userDb.userDao().getAll()

                        Log.d("AllUsers", users.toString())

                        withContext(Dispatchers.Main) {
                            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                            Toast.makeText(this@LoginActivity, message, Toast.LENGTH_LONG).show()
                            finish()
                        }
                    }
                } else {
                    HideLoading()
                    ShowAlert(message)
                }
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                Log.d("Error", t.message.toString())
            }
        })
    }
}


