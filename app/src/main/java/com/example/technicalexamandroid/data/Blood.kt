package com.example.technicalexamandroid.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "BloodTable")
data class Blood(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val location: String,
    val mobile: String,
    val bloodType: String)
