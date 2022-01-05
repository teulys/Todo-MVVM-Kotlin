package com.tjsoft.todolist.model.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.tjsoft.todolist.model.Entities.TodoModel

@Dao
interface TodoDao {

    @Query("SELECT * FROM todo")
    fun getAll() : List<TodoModel>

    @Query("SELECT * FROM todo WHERE id = :id")
    fun getById(id: Int) : TodoModel

    @Insert
    fun insertAll(vararg todo: TodoModel)

    @Update
    fun update(todo: TodoModel)

    @Delete
    fun delete(todo: TodoModel)
}