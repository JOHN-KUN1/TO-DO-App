package com.john.to_doapp.View

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.john.to_doapp.Application.ToDoApplication
import com.john.to_doapp.Model.ToDo
import com.john.to_doapp.R
import com.john.to_doapp.ViewModel.TodoViewModel
import com.john.to_doapp.databinding.ActivityInsertBinding

class InsertActivity : AppCompatActivity() {

    lateinit var insertBinding : ActivityInsertBinding
    lateinit var viewModel: TodoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        insertBinding = ActivityInsertBinding.inflate(layoutInflater)
        val view = insertBinding.root
        setContentView(view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        viewModel = TodoViewModel((application as ToDoApplication).repository)

        insertBinding.buttonInsert.setOnClickListener {
            getAndSaveData()
            finish()
        }
    }

    fun getAndSaveData(){

        val todoTitle = insertBinding.editTextInsertTitle.text.toString()
        val todoDescription = insertBinding.editTextInsertDescription.text.toString()

        val newTodo = ToDo(todoTitle,todoDescription)

        viewModel.Insert(newTodo)

    }
}