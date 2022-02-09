package com.example.technicalexamandroid.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Blood::class], version = 1, exportSchema = false)
abstract class BloodDatabase: RoomDatabase() {

    abstract fun bloodDao(): BloodDao

    companion object{
        @Volatile  // writes to this field are immediately visible to other threads
        private var INSTANCE: BloodDatabase? = null

        fun getDatabase(context: Context): BloodDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){  // protection from concurrent execution on multiple threads
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BloodDatabase::class.java,
                    "my_database"
                )
                    .createFromAsset("database/BloodTable.db")
//                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}