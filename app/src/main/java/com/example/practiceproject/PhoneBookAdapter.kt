package com.example.practiceproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PhoneBookAdapter(val item : ArrayList<PhoneBookEntities>) : RecyclerView.Adapter<PhoneBookAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val phoneNumberTextView: TextView = itemView.findViewById(R.id.phoneNumber)
        fun bind(item : PhoneBookEntities){
            phoneNumberTextView.text = item.number.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return item.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(item.get(position))
    }
}