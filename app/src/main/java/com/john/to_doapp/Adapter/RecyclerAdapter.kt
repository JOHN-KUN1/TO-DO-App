package com.john.to_doapp.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.john.to_doapp.databinding.TodoDesignBinding
import com.john.to_doapp.Model.ToDo
import com.john.to_doapp.View.InsertActivity
import com.john.to_doapp.View.UpdateActivity


class RecyclerAdapter(val context : Context): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    var todos : List<ToDo> = ArrayList()

    class ViewHolder(val itemBinding: TodoDesignBinding) : RecyclerView.ViewHolder(itemBinding.root){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = TodoDesignBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return todos.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemBinding.textViewTitle.text = todos[position].title
        holder.itemBinding.textViewDescription.text = todos[position].description


        val todoTitle = todos[position].title
        val todoDescription = todos[position].description
        val todoId = todos[position].id

        holder.itemBinding.ToDoDesignView.setOnClickListener {
            val intent = Intent(context,UpdateActivity::class.java)
            intent.putExtra("title",todoTitle)
            intent.putExtra("description",todoDescription)
            intent.putExtra("todoId",todoId)
            context.startActivity(intent)

        }

    }

    fun setTodo(todo : List<ToDo>){
        this.todos = todo
        notifyDataSetChanged()
    }

    fun getTodoAtPosition(position: Int): ToDo{
        return todos[position]
    }


}