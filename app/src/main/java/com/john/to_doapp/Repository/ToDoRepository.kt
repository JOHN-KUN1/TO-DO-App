package com.john.to_doapp.Repository

import androidx.annotation.WorkerThread
import com.john.to_doapp.ROOM.ToDoDao
import com.john.to_doapp.Model.ToDo
import kotlinx.coroutines.flow.Flow

class ToDoRepository(private val toDoDao: ToDoDao) {

    val allTodo : Flow<List<ToDo>> = toDoDao.getAllTodo()

    @WorkerThread
    suspend fun insert(toDo: ToDo){
        toDoDao.Insert(toDo)
    }

    @WorkerThread
    suspend fun update(toDo: ToDo){
        toDoDao.Update(toDo)
    }

    @WorkerThread
    suspend fun delete(toDo: ToDo){
        toDoDao.Delete(toDo)
    }

    @WorkerThread
    suspend fun deleteAll(){
        toDoDao.deleteAllTodo()
    }


}