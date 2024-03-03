package com.example.practiceproject

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [PhoneBookEntities::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getPhoneBookDAO() : PhoneBookDAO
    companion object{
        @Volatile
        private var INSTANCE : AppDatabase? = null

        private fun buildDatabase(context : Context): AppDatabase =
            Room.databaseBuilder(context.applicationContext,AppDatabase::class.java,"phone-book").build()
        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }
    }
}