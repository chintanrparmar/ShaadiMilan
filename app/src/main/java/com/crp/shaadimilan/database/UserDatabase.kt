package com.crp.shaadimilan.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.crp.shaadimilan.model.UserModel


@Database(version = 1, entities = [UserModel::class], exportSchema = false)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao


    companion object {

        @Volatile
        private var INSTANCE: UserDatabase? = null

        fun getDatabase(
            context: Context
        ): UserDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this)
            {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "user_database"
                )
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}