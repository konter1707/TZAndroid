package com.example.tzandroid.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tzandroid.R
import com.example.tzandroid.comon.EditingData
import com.example.tzandroid.room.workes.WorkesModel

class WorkesAdapter(
    val onListWorkesListener: OnListWorkesListener,
    private val list: List<WorkesModel>

) :
    RecyclerView.Adapter<WorkesAdapter.WorkesHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkesHolder {
        return WorkesHolder(
            LayoutInflater.from(parent.context),
            parent
        )
    }

    override fun getItemCount(): Int {
        return list.count()
    }

    override fun onBindViewHolder(holder: WorkesHolder, position: Int) {
        holder.bind(list[position])
        holder.itemView.setOnClickListener { onListWorkesListener.onWorkes(list[position]) }

    }

    interface OnListWorkesListener {
        fun onWorkes(workesModel: WorkesModel)
    }

    class WorkesHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.workers_item, parent, false)) {
        private var avatar:ImageView?=null
        private var fname_lname: TextView? = null
        private var age: TextView? = null

        init {
            avatar=itemView.findViewById(R.id.avatar)
            fname_lname = itemView.findViewById(R.id.fname_lname)
            age = itemView.findViewById(R.id.birthday_age)
        }

        fun bind(workesModel: WorkesModel) {
            if (workesModel.avatr_url !="-")
                Glide.with(avatar!!.context).load(workesModel.avatr_url).centerCrop().placeholder(android.R.drawable.spinner_background).into(avatar as ImageView)
            else avatar?.setImageResource(R.drawable.user48)
            fname_lname?.text = workesModel.f_name.plus("\n").plus(workesModel.l_name)
            age?.text = EditingData().getYear(workesModel.birthday)
        }
    }
}