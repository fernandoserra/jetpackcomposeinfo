package com.example.jetpackcomposeinfo.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.jetpackcomposeinfo.application.AppConstants
import com.example.jetpackcomposeinfo.data.local.team.TeamDao
import com.example.jetpackcomposeinfo.data.local.team.TeamLocal

@Database(entities = [TeamLocal::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun teamDao(): TeamDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {

            INSTANCE = INSTANCE ?: Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                AppConstants.DATABASE_NAME
            ).build()

            return INSTANCE!!
        }
    }
}
