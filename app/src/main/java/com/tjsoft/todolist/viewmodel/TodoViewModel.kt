package com.tjsoft.todolist.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tjsoft.todolist.model.AppDatabase
import com.tjsoft.todolist.model.Entities.TodoModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodoViewModel(val context: Context) : ViewModel() {

    private var todoList: MutableLiveData<List<TodoModel>> = MutableLiveData()
    private var db : AppDatabase = AppDatabase.getDB(context)

    fun addTodo(taskName: String) {
        val todo = TodoModel(name = taskName)

        CoroutineScope(Dispatchers.IO).launch {
            db.todoDao().insertAll(todo)
            refrestTodo()
        }
    }

    fun getAllTodo() : LiveData<List<TodoModel>> {

        CoroutineScope(Dispatchers.IO).launch {
            val tdList = db.todoDao().getAll()

            todoList.postValue(tdList)
        }
        return todoList
    }

    private fun refrestTodo(){
        val tdList = db.todoDao().getAll()

        todoList.postValue(tdList)
    }

    fun deleteTodo(todo: TodoModel){
        CoroutineScope(Dispatchers.IO).launch {
            db.todoDao().delete(todo)
            refrestTodo()
        }
    }

    fun updateTodo(todo: TodoModel) {
        CoroutineScope(Dispatchers.IO).launch {
            db.todoDao().update(todo)
            refrestTodo()
        }
    }

}