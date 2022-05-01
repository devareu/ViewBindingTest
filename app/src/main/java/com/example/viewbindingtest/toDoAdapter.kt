package com.example.viewbindingtest

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.viewbindingtest.databinding.*

private lateinit var itBinding: ItemTodoBinding

class toDoAdapter (private val todos: MutableList<toDoItem>) : RecyclerView.Adapter<toDoAdapter.TodoViewHolder>() {
    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.item_todo,parent,false)
        return TodoViewHolder(inflatedView)
        //return TodoViewHolder(
        //    LayoutInflater.from(parent.context).inflate(R.layout.item_todo,parent,false)
        //)
    }

    fun addTodo(todo: toDoItem) {
        todos.add(todo)
        notifyItemInserted(todos.size - 1)
    }

    fun deleteDoneTodos() {
        todos.removeAll { todo ->
            todo.isChecked
        }
        notifyDataSetChanged()
    }

    private fun toggleStrikeThrough(tvTodoTitle: TextView, isChecked: Boolean) {
        if(isChecked) {
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else {
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val curTodo = todos[position]
        holder.itemView.apply {
            itBinding.tvTodoTitle.text = curTodo.title
            itBinding.cbDone.isChecked = curTodo.isChecked
            toggleStrikeThrough(itBinding.tvTodoTitle, curTodo.isChecked)
            itBinding.cbDone.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThrough(itBinding.tvTodoTitle, isChecked)
                curTodo.isChecked = !curTodo.isChecked
            }
        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }
}