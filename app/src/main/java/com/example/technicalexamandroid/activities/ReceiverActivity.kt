package com.example.technicalexamandroid.activities

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.technicalexamandroid.adapter.RVAdapterReceiver
import com.example.technicalexamandroid.data.Blood
import com.example.technicalexamandroid.data.BloodDatabase
import com.example.technicalexamandroid.data.BloodViewModel
import com.example.technicalexamandroid.databinding.ActivityReceiverBinding

class ReceiverActivity : AppCompatActivity() {
    lateinit var binding: ActivityReceiverBinding
    lateinit var bloodViewModel: BloodViewModel

    private lateinit var rvAdapterReceiver: RVAdapterReceiver
    private lateinit var items: List<Blood>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReceiverBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Room.databaseBuilder(applicationContext, BloodDatabase::class.java, "my_database")
            .createFromAsset("database/BloodTable.db")
            .fallbackToDestructiveMigration()
            .build()

        items = listOf()

        bloodViewModel = ViewModelProvider(this).get(BloodViewModel::class.java)
        bloodViewModel.getBlood().observe(this, {
            blood -> rvAdapterReceiver.update(blood)
        })

        rvAdapterReceiver = RVAdapterReceiver(this)
        binding.rvReceiver.adapter = rvAdapterReceiver
        binding.rvReceiver.layoutManager = LinearLayoutManager(this)
    }
}