package com.example.tzandroid.room.workes

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.tzandroid.parseJson.Specialty

@Entity
data class WorkesModel(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val specialtyId:Long,
    val f_name: String,
    val l_name: String,
    val birthday: String,
    val avatr_url: String)
data class ModelWorkes(
    val f_name: String,
    val l_name: String,
    val birthday: String
)