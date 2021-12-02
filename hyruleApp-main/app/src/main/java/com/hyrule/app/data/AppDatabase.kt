package com.hyrule.app.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [HyruleEntity::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class)

abstract class AppDatabase : RoomDatabase() {

    abstract fun entityDao(): EntityDao?

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "hyruleapp.db"
                    ).build()
                }
            }
            return INSTANCE
        }
    }
}