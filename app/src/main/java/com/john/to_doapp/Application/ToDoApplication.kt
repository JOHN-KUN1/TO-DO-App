package com.john.to_doapp.Application

import android.app.Application
import com.john.to_doapp.Repository.ToDoRepository
import com.john.to_doapp.ROOM.ToDoDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class ToDoApplication : Application() {

    val applicationScope = CoroutineScope(SupervisorJob())
    val database by lazy { ToDoDatabase.getDatabase(this,applicationScope) }
    val repository by lazy { ToDoRepository(database.getDao()) }

}