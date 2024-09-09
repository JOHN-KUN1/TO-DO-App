package com.john.to_doapp.View

import android.content.ClipData.Item
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.john.to_doapp.Adapter.RecyclerAdapter
import com.john.to_doapp.Application.ToDoApplication
import com.john.to_doapp.R
import com.john.to_doapp.databinding.ActivityMainBinding
import com.john.to_doapp.ViewModel.TodoViewModel
import com.john.to_doapp.ViewModel.ViewModelFactory


class MainActivity : AppCompatActivity() {

    lateinit var todoViewModel: TodoViewModel
    lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = mainBinding.root
        setContentView(view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val todoFactory = ViewModelFactory((application as ToDoApplication).repository)
        todoViewModel = ViewModelProvider(this,todoFactory).get(TodoViewModel::class.java)
        val adapter = RecyclerAdapter(this)
        mainBinding.recyclerView.layoutManager = LinearLayoutManager(this)
        mainBinding.recyclerView.adapter = adapter
        todoViewModel.myTodos.observe(this, Observer {todo ->
            //update the ui
            adapter.setTodo(todo)
        })

        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                TODO("Not yet implemented")
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val todo = adapter.getTodoAtPosition(viewHolder.adapterPosition)
                todoViewModel.Delete(todo)
                Toast.makeText(applicationContext,"Your Todo has been deleted",Toast.LENGTH_SHORT).show()
            }

        }).attachToRecyclerView(mainBinding.recyclerView)

        mainBinding.floatingActionButton.setOnClickListener {

            val intent = Intent(this@MainActivity,InsertActivity::class.java)
            startActivity(intent)
        }

    }
}