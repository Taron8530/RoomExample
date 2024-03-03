package com.example.practiceproject

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "PhoneBook")
data class PhoneBookEntities(
    @PrimaryKey @ColumnInfo(name = "phoneNumber") val number : String,
)