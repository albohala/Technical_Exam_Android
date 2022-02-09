package com.example.technicalexamandroid.activities

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.technicalexamandroid.R
import com.example.technicalexamandroid.adapter.RVAdapterReceiver
import com.example.technicalexamandroid.adapter.RVAdapterAdmin
import com.example.technicalexamandroid.data.Blood
import com.example.technicalexamandroid.data.BloodViewModel
import com.example.technicalexamandroid.databinding.ActivityAdminBinding
import com.example.technicalexamandroid.databinding.ActivityDonorSignUpBinding

class AdminActivity : AppCompatActivity() {
    lateinit var binding: ActivityAdminBinding
    lateinit var bindingSignUp: ActivityDonorSignUpBinding
    lateinit var bloodViewModel: BloodViewModel

    private lateinit var rvAdapter: RVAdapterAdmin
    private lateinit var items: List<Blood>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminBinding.inflate(layoutInflater)
        bindingSignUp = ActivityDonorSignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        items = listOf()

        bloodViewModel = ViewModelProvider(this).get(BloodViewModel::class.java)
        bloodViewModel.getBlood().observe(this, {
                blood -> rvAdapter.update(blood)
        })

        rvAdapter = RVAdapterAdmin(this)
        binding.rvAdmin.adapter = rvAdapter
        binding.rvAdmin.layoutManager = LinearLayoutManager(this)
    }

    private fun addDataToDatabase() {
        var name = "Name: " + bindingSignUp.tvName.text.toString()
        var location = "Location: " + bindingSignUp.etLocation.text.toString()
        var mobile = "Mobile: " + bindingSignUp.etMobile.text.toString()
        var bloodType = "Blood Type: " + bindingSignUp.etBloodType.text.toString()

        if(checkInput(name, location, mobile, bloodType)){
            val donorData = Blood(0, name, location, mobile, bloodType)
            bloodViewModel.editBlood(donorData)
            Toast.makeText(applicationContext, "The data is updated", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(applicationContext, "The data was NOT updated", Toast.LENGTH_LONG).show()
        }
    }

    private fun checkInput(name: String, location: String, mobile: String, bloodType: String): Boolean {
        return !(TextUtils.isEmpty(name) && TextUtils.isEmpty(location) && TextUtils.isEmpty(mobile) && TextUtils.isEmpty(bloodType))
    }

    fun dialogAlert(){
        val dialogBuilder = AlertDialog.Builder(this)

        val updateName = EditText(this)
        val updateLocation = EditText(this)
        val updateBloodType = EditText(this)
        val updateMobile = EditText(this)

        var name = updateName.text.toString()
        var location = updateLocation.text.toString()
        var bloodType = updateBloodType.text.toString()
        var mobile = updateMobile.text.toString()

        updateName.hint = "Change Name"
        updateLocation.hint = "Change Location"
        updateBloodType.hint = "Change Blood Type"
        updateMobile.hint = "Change Mobile"

        dialogBuilder
            .setCancelable(false)
            .setPositiveButton("Save", DialogInterface.OnClickListener {
                    _, _ -> addDataToDatabase()
            })
            .setNegativeButton("Cancel", DialogInterface.OnClickListener {
                    dialog, _ -> dialog.cancel()
            })
        val alert = dialogBuilder.create()
        alert.setTitle("Update Item")

        val layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
        )
        layoutParams.setMargins(20, 20, 20, 20) //set margin

        val lp = LinearLayout(applicationContext)
        lp.orientation = LinearLayout.VERTICAL

        lp.addView(updateName, layoutParams)
        lp.addView(updateLocation, layoutParams)
        lp.addView(updateBloodType, layoutParams)
        lp.addView(updateMobile, layoutParams)
        alert.setView(lp)
        alert.show()
    }
}