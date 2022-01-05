package com.tjsoft.todolist.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.tjsoft.todolist.R
import com.tjsoft.todolist.databinding.ItemTodoBinding
import com.tjsoft.todolist.model.AppDatabase
import com.tjsoft.todolist.model.Entities.TodoModel
import com.tjsoft.todolist.viewmodel.TodoViewModel

class TodoAdapter(private val todoList: MutableList<TodoModel>,
                  private val context: Context) : RecyclerView.Adapter<TodoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val inflate = LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent, false)
        return TodoViewHolder(inflate, context)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(todoList[position])
    }

    override fun getItemCount(): Int {
        return todoList.size
    }
}

class TodoViewHolder(itemView: View, private val context: Context) : RecyclerView.ViewHolder(itemView) {

    private val binding = ItemTodoBinding.bind(itemView)

    fun bind(todo: TodoModel){
        binding.cbTodoStatus.isChecked = todo.status
        binding.tvTodoName.text = todo.name

        setCheckLisener(binding.cbTodoStatus, todo)
        setDeleteButton(binding.ivButtomDelete, todo)
    }

    private fun setCheckLisener(checkBox: CheckBox, todo: TodoModel) {

        checkBox.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { compoundButton, b ->
            val vm = TodoViewModel(context)
            todo.status = b
            vm.updateTodo(todo)
        })
    }

    private fun setDeleteButton(iv: ImageView, todo: TodoModel) {
        iv.setOnClickListener {
            val vm = TodoViewModel(context)
            vm.deleteTodo(todo)
        }
    }
}
