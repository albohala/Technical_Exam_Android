package com.example.technicalexamandroid.data

import androidx.lifecycle.LiveData

class BloodRepository(private val bloodDao: BloodDao) {

    val getBlood: LiveData<List<Blood>> = bloodDao.getBlood()

    suspend fun addBlood(blood: Blood){
        bloodDao.addBlood(blood)
    }

    suspend fun updateBlood(blood: Blood){
        bloodDao.updateBlood(blood)
    }

    suspend fun deleteBlood(blood: Blood){
        bloodDao.deleteBlood(blood)
    }
}