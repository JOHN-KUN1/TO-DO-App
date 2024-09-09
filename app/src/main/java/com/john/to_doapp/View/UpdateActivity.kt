package com.john.to_doapp.View

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.john.to_doapp.Adapter.RecyclerAdapter
import com.john.to_doapp.Application.ToDoApplication
import com.john.to_doapp.Model.ToDo
import com.john.to_doapp.R
import com.john.to_doapp.ViewModel.TodoViewModel
import com.john.to_doapp.databinding.ActivityUpdateBinding

class UpdateActivity : AppCompatActivity() {

    lateinit var updateBinding: ActivityUpdateBinding
    lateinit var viewModel: TodoViewModel
    lateinit var adapter : RecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        updateBinding = ActivityUpdateBinding.inflate(layoutInflater)
        val view = updateBinding.root
        setContentView(view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        getAndShowData()

        viewModel = TodoViewModel((application as ToDoApplication).repository)

        updateBinding.buttonUpdate.setOnClickListener {
            getAndUpdateData()
            finish()
        }

    }

    fun getAndShowData(){

        val todoTitle = intent.getStringExtra("title").toString()
        val todoDescription = intent.getStringExtra("description").toString()

        updateBinding.editTextUpdateTitle.setText("$todoTitle")
        updateBinding.editTextUpdateDescription.setText("$todoDescription")

    }

    fun getAndUpdateData(){

        val newTitle = updateBinding.editTextUpdateTitle.text.toString()
        val newDescription = updateBinding.editTextUpdateDescription.text.toString()
        val todoId = intent.getIntExtra("todoId",0)

        val updatedTodo = ToDo(newTitle,newDescription)
        updatedTodo.id = todoId
        viewModel.Update(updatedTodo)
    }

}