package com.example.viewbindingtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.viewbindingtest.databinding.*


class MainActivity : AppCompatActivity() {

    private lateinit var amBinding: ActivityMainBinding
    private lateinit var todoAdapter: toDoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        amBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
        todoAdapter = toDoAdapter(mutableListOf())

        amBinding.rvTodoItems.adapter = todoAdapter
        amBinding.rvTodoItems.layoutManager = LinearLayoutManager(this)

        amBinding.btnAddTodo.setOnClickListener {
            Toast.makeText(this, "Made it here.", Toast.LENGTH_SHORT).show()
            val todoTitle = amBinding.etToDoTitle.text.toString()
            if(todoTitle.isNotEmpty()) {
                val todo = toDoItem(todoTitle)
                todoAdapter.addTodo(todo)
                amBinding.etToDoTitle.text.clear()
            }
        }
        amBinding.btnDeleteDoneTodo.setOnClickListener {
            todoAdapter.deleteDoneTodos()
        }
    }
}