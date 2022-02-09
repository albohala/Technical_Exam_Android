package com.example.technicalexamandroid.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.technicalexamandroid.activities.AdminActivity
import com.example.technicalexamandroid.data.Blood
import com.example.technicalexamandroid.databinding.ItemRowAdminBinding

class RVAdapterAdmin(private val activity: AdminActivity):  RecyclerView.Adapter<RVAdapterAdmin.ItemViewHolder>(){
    private var items = emptyList<Blood>()
    class ItemViewHolder(val binding: ItemRowAdminBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(ItemRowAdminBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val bloodItem = items[position]

        holder.binding.apply {
            tvName.text = bloodItem.name
            tvLocation.text = bloodItem.location
            tvBloodType.text = bloodItem.bloodType
            tvMobile.text = bloodItem.mobile

            btnEdit.setOnClickListener {
                activity.dialogAlert()
            }
            btnDelete.setOnClickListener {
                activity.bloodViewModel.deleteBlood(bloodItem.id)
            }
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