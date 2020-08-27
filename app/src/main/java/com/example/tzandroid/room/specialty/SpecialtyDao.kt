package com.example.tzandroid.room.specialty

import android.security.identity.AccessControlProfileId
import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface SpecialtyDao {
    @Insert
    fun insertSpecialty(specialtyModel: SpecialtyModel): Completable

    @Query("SELECT nameSpecialty, specialtyId  FROM specialtymodel")
    fun getSpecialty(): Single<List<NameSpecialty>>
}

