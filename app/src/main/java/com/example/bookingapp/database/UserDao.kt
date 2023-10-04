package com.example.bookingapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao {

    @Query("SELECT * FROM users")
    suspend fun getAll(): List<Users>

    @Insert
    suspend fun insertAll(users: List<Users>)

    @Update
    suspend fun updateAll(users: List<Users>)

    @Delete
    suspend fun delete(users: Users)

    @Query("delete from users")
    suspend fun deleteAllUsers()

    @Query("select * from users order by itemId desc")
    suspend fun getAllUsers(): LiveData<List<Users>>
}