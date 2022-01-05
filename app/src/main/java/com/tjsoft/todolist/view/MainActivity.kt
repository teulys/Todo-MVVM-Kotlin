package com.tjsoft.todolist.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.tjsoft.todolist.databinding.ActivityMainBinding
import com.tjsoft.todolist.model.Entities.TodoModel
import com.tjsoft.todolist.view.adapter.TodoAdapter
import com.tjsoft.todolist.viewmodel.TodoViewModel

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private var todoList: MutableList<TodoModel> = mutableListOf()
    private lateinit var viewModel: TodoViewModel
    private lateinit var adapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = TodoViewModel(this)
        adapter = TodoAdapter(todoList,this)

        viewModel.getAllTodo().observe(this, Observer {
            if (!it.isNullOrEmpty()) {
                todoList.clear()
                todoList.addAll(it)
                adapter.notifyDataSetChanged()
            }
        })

        binding.ivButtomAdd.setOnClickListener(this)

        binding.rvTodoList.layoutManager = LinearLayoutManager(this)
        binding.rvTodoList.adapter = adapter
    }

    override fun onClick(view: View?) {
        if (view == binding.ivButtomAdd) {

            val text : String? = binding.etTodoName.text.toString()

            if (text.isNullOrEmpty()) {
                Toast.makeText(this, "Write some to add", Toast.LENGTH_LONG).show()
            } else {
                viewModel.addTodo(text)
            }
        }
    }
}