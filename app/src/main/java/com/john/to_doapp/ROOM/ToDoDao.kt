package com.john.to_doapp.ROOM

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.john.to_doapp.Model.ToDo
import kotlinx.coroutines.flow.Flow


@Dao
interface ToDoDao {

    @Insert
    suspend fun Insert(toDo: ToDo)

    @Update
    suspend fun Update(toDo: ToDo)

    @Delete
    suspend fun Delete(toDo: ToDo)

    @Query("DELETE FROM todo_table")
    suspend fun deleteAllTodo()

    @Query("SELECT * FROM todo_table ORDER BY id ASC")
    fun getAllTodo() : Flow<List<ToDo>>


}