package com.example.tzandroid.room

import android.content.Context
import android.widget.GridLayout
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.tzandroid.room.specialty.SpecialtyDao
import com.example.tzandroid.room.specialty.SpecialtyModel
import com.example.tzandroid.room.workes.WorkesDao
import com.example.tzandroid.room.workes.WorkesModel


@Database(entities = [WorkesModel::class,SpecialtyModel::class], version = 2)
abstract class WorkesDB : RoomDatabase() {
    abstract fun getWorkesDao(): WorkesDao
    abstract fun getSpecialtyDao():SpecialtyDao
    companion object {
        var INSTANCE: WorkesDB? = null
        fun getAppDateBase(context: Context): WorkesDB? {
            if (INSTANCE == null) {
                synchronized(WorkesDB::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        WorkesDB::class.java,
                        "myDB"
                    ).build()
                }
            }
            return INSTANCE
        }
    }
}