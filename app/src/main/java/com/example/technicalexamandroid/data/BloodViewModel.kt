package com.example.technicalexamandroid.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BloodViewModel(application: Application): AndroidViewModel(application){

    private val blood: LiveData<List<Blood>>
    private val repository: BloodRepository

    init {
        val bloodDao = BloodDatabase.getDatabase(application).bloodDao()
        repository = BloodRepository(bloodDao)
        blood = repository.getBlood
    }

    fun getBlood(): LiveData<List<Blood>>{
        return blood
    }

    fun addBlood(blood: Blood){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addBlood(blood)
        }
    }

    fun editBlood(blood: Blood){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateBlood(blood)
        }
    }

    fun deleteBlood(id: Int){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteBlood(Blood(id, "", "", "", ""))
        }
    }
}