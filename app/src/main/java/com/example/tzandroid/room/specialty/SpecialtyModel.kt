package com.example.tzandroid.room.specialty

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SpecialtyModel(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val nameSpecialty: String,
    val specialtyId: Long
)

data class NameSpecialty ( val nameSpecialty: String,
                           val specialtyId: Long)