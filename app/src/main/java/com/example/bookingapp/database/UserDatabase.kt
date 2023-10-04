package com.example.bookingapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Users::class], version = 1)
abstract class UserDatabase : RoomDatabase() {

    abstract fun UserDao(): UserDao

    companion object {
        private var instance: UserDatabase? = null

        fun getInstance(ctx: Context): UserDatabase {
            if (instance == null)
                instance = Room.databaseBuilder(ctx.applicationContext, UserDatabase::class.java, "users")
                        .fallbackToDestructiveMigration()
                        .addCallback(roomCallback)
                        .build()
            return instance!!
        }

        private val roomCallback = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                suspend {
                    populateDatabase(instance!!)
                }
            }
        }

        private suspend fun populateDatabase(db: UserDatabase) {
            val userDao = db.UserDao()
//            userDao.insertAll()
        }
    }

}