package com.example.practiceproject

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PhoneBookDAO {
    @Query("SELECT * FROM phonebook")
    fun getPhoneBook(): List<PhoneBookEntities>    // 등록된 회원인지 확인

    @Insert
    fun insertPhoneBook(schedule: PhoneBookEntities)    // 회원 등록

}