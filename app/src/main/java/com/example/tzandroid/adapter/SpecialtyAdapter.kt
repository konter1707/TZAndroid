package com.example.tzandroid.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tzandroid.R
import com.example.tzandroid.room.specialty.NameSpecialty

class SpecialtyAdapter(
    val onShowListSpecialtyListener: OnShowListSpecialtyListener,
    private val list: List<NameSpecialty>

) :
    RecyclerView.Adapter<SpecialtyAdapter.SpecialtyHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpecialtyHolder {
        return SpecialtyHolder(
            LayoutInflater.from(parent.context),
            parent
        )
    }

    override fun getItemCount(): Int {
        return list.count()
    }

    override fun onBindViewHolder(holder: SpecialtyHolder, position: Int) {
        holder.bind(list[position])
        holder.itemView.setOnClickListener { onShowListSpecialtyListener.onShow(list[position]) }

    }

    interface OnShowListSpecialtyListener {
        fun onShow(name: NameSpecialty)
    }

    class SpecialtyHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.specialty_item, parent, false)) {
        private var nameSpecialty: TextView? = null

        init {
            nameSpecialty = itemView.findViewById(R.id.workingText)
        }
        fun bind(name: NameSpecialty) {
            nameSpecialty?.text =name.nameSpecialty
        }
    }
}