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

}

private lateinit var INSTANCE: DB

fun getDatabase(context: Context) : DB {
    synchronized(DB::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(context.applicationContext,
                DB::class.java,
                "random_person_db").build()
        }
    }
    return INSTANCE
}