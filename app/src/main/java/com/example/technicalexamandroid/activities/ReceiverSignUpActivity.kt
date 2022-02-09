package com.example.technicalexamandroid.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.RadioButton
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.technicalexamandroid.data.Blood
import com.example.technicalexamandroid.data.BloodViewModel
import com.example.technicalexamandroid.databinding.ActivityReceiverSignUpBinding

class ReceiverSignUpActivity : AppCompatActivity() {
    lateinit var bloodViewModel: BloodViewModel
    lateinit var binding: ActivityReceiverSignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReceiverSignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bloodViewModel = ViewModelProvider(this).get(BloodViewModel::class.java)

        binding.rgQuestion.setOnCheckedChangeListener { radioGroup, i ->
            val radioBtn = binding.rgQuestion.checkedRadioButtonId
            val yesBtn = binding.rbYes.id

            if(radioBtn == yesBtn){
                binding.btnSignUp.setOnClickListener {
                    addDataToDatabase()
                    val intent = Intent(this, DonorActivity::class.java)
                    startActivity(intent)
                }
            } else {
                binding.btnSignUp.setOnClickListener {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
            }
        }

//        binding.btnSignUp.setOnClickListener {
//            addDataToDatabase()
//            val intent = Intent(this, DonorActivity::class.java)
//            startActivity(intent)
//        }
    }

    private fun addDataToDatabase() {
        val name = "Name: " + binding.etName.text.toString()
        val location = "Location: " + binding.etLocation.text.toString()
        val mobile = "Mobile: " + binding.etMobile.text.toString()
        val bloodType = "Blood Type: " + binding.etBloodType.text.toString()

        if(checkInput(name, location, mobile, bloodType)){
            val donorData = Blood(0, name, location, mobile, bloodType)
            bloodViewModel.addBlood(donorData)
            Toast.makeText(applicationContext, "Your data saved in our Database", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(applicationContext, "The data was NOT added", Toast.LENGTH_LONG).show()
        }
    }

    private fun checkInput(name: String, location: String, mobile: String, bloodType: String): Boolean {
        return !(TextUtils.isEmpty(name) && TextUtils.isEmpty(location) && TextUtils.isEmpty(mobile) && TextUtils.isEmpty(bloodType))
    }
}