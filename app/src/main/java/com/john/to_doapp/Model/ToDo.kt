package com.john.to_doapp.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_table")
class ToDo(
    var title : String,
    var description : String
) {
    @PrimaryKey(autoGenerate = true)
    var id = 0
}