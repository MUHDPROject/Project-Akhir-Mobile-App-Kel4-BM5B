package com.kel4.absensi.database

import androidx.room.Database
import com.kel4.absensi.model.ModelDatabase
import androidx.room.RoomDatabase
import com.kel4.absensi.database.dao.DatabaseDao

@Database(entities = [ModelDatabase::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun databaseDao(): DatabaseDao?
}