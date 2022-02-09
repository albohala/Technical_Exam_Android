package com.example.technicalexamandroid.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.technicalexamandroid.R
import com.example.technicalexamandroid.data.Blood
import com.example.technicalexamandroid.data.BloodViewModel
import com.example.technicalexamandroid.databinding.ActivityDonorSignUpBinding

class DonorSignUpActivity : AppCompatActivity() {
    lateinit var bloodViewModel: BloodViewModel
    lateinit var binding: ActivityDonorSignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDonorSignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bloodViewModel = ViewModelProvider(this).get(BloodViewModel::class.java)

        binding.btnSignUp.setOnClickListener {
            addDataToDatabase()
        }
    }

    private fun addDataToDatabase() {
        var name = "Name: " + binding.etName.text.toString()
        var location = "Location: " + binding.etLocation.text.toString()
        var mobile = "Mobile: " + binding.etMobile.text.toString()
        var bloodType = "Blood Type: " + binding.etBloodType.text.toString()

        if(checkInput(name, location, mobile, bloodType)){
            val donorData = Blood(0, name, location, mobile, bloodType)
            bloodViewModel.addBlood(donorData)
            Toast.makeText(applicationContext, "Your data saved in our Database", Toast.LENGTH_LONG).show()
            finish()
        } else {
            Toast.makeText(applicationContext, "The data was NOT added", Toast.LENGTH_LONG).show()
        }
    }

    private fun checkInput(name: String, location: String, mobile: String, bloodType: String): Boolean {
        return !(TextUtils.isEmpty(name) && TextUtils.isEmpty(location) && TextUtils.isEmpty(mobile) && TextUtils.isEmpty(bloodType))
    }
}