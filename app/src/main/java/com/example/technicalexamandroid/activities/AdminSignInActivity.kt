package com.example.technicalexamandroid.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.technicalexamandroid.data.BloodViewModel
import com.example.technicalexamandroid.databinding.ActivityAdminBinding
import com.example.technicalexamandroid.databinding.ActivityAdminSignInBinding
import com.example.technicalexamandroid.databinding.ActivityDonorSignUpBinding

class AdminSignInActivity : AppCompatActivity() {
    lateinit var binding: ActivityAdminSignInBinding
    lateinit var bloodViewModel: BloodViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminSignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}