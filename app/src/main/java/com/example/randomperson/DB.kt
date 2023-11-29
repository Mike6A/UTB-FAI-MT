package com.example.randomperson

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.randomperson.dao.PersonDAO
import com.example.randomperson.entity.PersonData

@Database(
    entities = [PersonData::class],
    version = 1
)
abstract class DB : RoomDatabase() {

    abstract fun personDAO() : PersonDAO

    companion object {
        @Volatile
        private var INSTANCE: DB? = null

        fun getDatabase(context: Context) : DB {
            val tempInstance = INSTANCE
            if (tempInstance != null)
                return tempInstance

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DB::class.java,
                    "random_person_db"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}