package com.example.technicalexamandroid.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.technicalexamandroid.adapter.RVAdapterDonor
import com.example.technicalexamandroid.data.Blood
import com.example.technicalexamandroid.data.BloodViewModel
import com.example.technicalexamandroid.databinding.ActivityDonorBinding

class DonorActivity : AppCompatActivity() {
    lateinit var binding: ActivityDonorBinding
    lateinit var bloodViewModel: BloodViewModel

    private lateinit var rvAdapter: RVAdapterDonor
    private lateinit var items: List<Blood>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDonorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        items = listOf()

        bloodViewModel = ViewModelProvider(this).get(BloodViewModel::class.java)
        bloodViewModel.getBlood().observe(this, {
                blood -> rvAdapter.update(blood)
        })

        rvAdapter = RVAdapterDonor(this)
        binding.rvDonor.adapter = rvAdapter
        binding.rvDonor.layoutManager = LinearLayoutManager(this)
    }
}