package com.example.technicalexamandroid.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.technicalexamandroid.activities.AdminActivity
import com.example.technicalexamandroid.activities.DonorActivity
import com.example.technicalexamandroid.data.Blood
import com.example.technicalexamandroid.databinding.ItemRowAdminBinding
import com.example.technicalexamandroid.databinding.ItemRowDonorBinding

class RVAdapterDonor (private val activity: DonorActivity):  RecyclerView.Adapter<RVAdapterDonor.ItemViewHolder>(){
    private var items = emptyList<Blood>()
    class ItemViewHolder(val binding: ItemRowDonorBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(ItemRowDonorBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val bloodItem = items[position]

        holder.binding.apply {
            tvName.text = bloodItem.name
            tvLocation.text = bloodItem.location
            tvBloodType.text = bloodItem.bloodType
            tvMobile.text = bloodItem.mobile
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun update(blood: List<Blood>){
        println("UPDATING DATA")
        this.items = blood
        notifyDataSetChanged()
    }
}