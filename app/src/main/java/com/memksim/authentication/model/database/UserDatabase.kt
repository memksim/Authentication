package com.memksim.authentication.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.memksim.authentication.model.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDatabase: RoomDatabase() {

    abstract fun getDao(): UserDao

    companion object{
        private var INSTANCE: UserDatabase? = null

        fun getDataBase(context: Context): UserDatabase{
            val template = INSTANCE
            if(template != null) return template

            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "usersDatabase"
                ).allowMainThreadQueries().build()
                INSTANCE = instance
                return instance
            }
        }
    }

}