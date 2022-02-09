package com.example.technicalexamandroid.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface BloodDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addBlood(blood: Blood)

    @Query("SELECT * FROM BloodTable ORDER BY id ASC")
    fun getBlood(): LiveData<List<Blood>>

    @Update
    suspend fun updateBlood(blood: Blood)

    @Delete
    suspend fun deleteBlood(blood: Blood)
}