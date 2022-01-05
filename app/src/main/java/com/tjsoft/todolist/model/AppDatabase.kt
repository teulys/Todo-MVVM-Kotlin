package com.tjsoft.todolist.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tjsoft.todolist.model.Dao.TodoDao
import com.tjsoft.todolist.model.Entities.TodoModel

@Database(entities = [TodoModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun todoDao() : TodoDao

    companion object {
        @Volatile
        private var INSTANCE : AppDatabase? = null

        fun getDB(context: Context) : AppDatabase{

            if (INSTANCE != null) {
                return INSTANCE!!
            }

            synchronized(this) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "todo-db").build()


                return INSTANCE!!
            }
        }
    }
}