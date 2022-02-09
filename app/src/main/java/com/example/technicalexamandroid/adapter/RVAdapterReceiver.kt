package com.example.technicalexamandroid.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.technicalexamandroid.activities.ReceiverActivity
import com.example.technicalexamandroid.activities.ReceiverSignUpActivity
import com.example.technicalexamandroid.data.Blood
import com.example.technicalexamandroid.databinding.ItemRowBinding

class RVAdapterReceiver(private val activity: ReceiverActivity):  RecyclerView.Adapter<RVAdapterReceiver.ItemViewHolder>(){
    private var items = emptyList<Blood>()
    class ItemViewHolder(val binding: ItemRowBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val bloodItem = items[position]

        holder.binding.apply {
            tvName.text = bloodItem.name
            tvBloodType.text = bloodItem.bloodType

            tvBloodType.setOnClickListener {
                val intent = Intent(activity, ReceiverSignUpActivity::class.java)
                activity.startActivity(intent)
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