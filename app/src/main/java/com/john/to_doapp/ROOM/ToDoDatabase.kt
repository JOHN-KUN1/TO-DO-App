package com.john.to_doapp.ROOM

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.john.to_doapp.Model.ToDo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [ToDo::class], version = 1)
abstract class ToDoDatabase : RoomDatabase() {

    abstract fun getDao() : ToDoDao

    //Singleton

    companion object {
        @Volatile
        var INSTANCE : ToDoDatabase? = null

        fun getDatabase(context : Context,scope: CoroutineScope) : ToDoDatabase {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ToDoDatabase::class.java,
                    "todo_database"
                ).addCallback(TodoDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }

    }

    class TodoDatabaseCallback(private val scope : CoroutineScope) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    val todoDao = database.getDao()
                    todoDao.Insert(ToDo("Title 1","Description 1"))
                    todoDao.Insert(ToDo("Title 2","Description 2"))
                    todoDao.Insert(ToDo("Title 3","Description 3"))
                }

            }
        }
    }

}