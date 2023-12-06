package com.example.bookingapp.database

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
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: Users)

    @Update
    suspend fun updateAll(users: List<Users>)

    @Delete
    suspend fun delete(users: Users)

    @Query("delete from users")
    suspend fun deleteAllUsers()
}