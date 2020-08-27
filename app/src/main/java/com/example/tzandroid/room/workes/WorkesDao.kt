package com.example.tzandroid.room.workes

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface WorkesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRx(workModel: WorkesModel): Completable

    @Query("SELECT * FROM workesmodel WHERE specialtyId LIKE :idSpecialty")
    fun getWorking(idSpecialty: Long): Single<List<WorkesModel>>
}
