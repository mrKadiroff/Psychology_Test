package com.shoxrux.psychology_tests.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.shoxrux.psychology_tests.room.scrores.ScoresDao
import com.shoxrux.psychology_tests.room.scrores.ScoresEntity

@Database(entities = [CategoryEntity::class,ScoresEntity::class], version = 2)
abstract class AppDatabase : RoomDatabase() {

    abstract fun categoryDao(): CategoryDao
    abstract fun scoresDao(): ScoresDao


    companion object {
        private var instance: AppDatabase? = null

        @Synchronized
        fun getInstance(context: Context): AppDatabase {
            if (instance == null){
                instance = Room.databaseBuilder(context,AppDatabase::class.java,"pdp.db")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }
            return instance!!
        }
    }
}