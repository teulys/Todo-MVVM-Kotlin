package com.tjsoft.todolist.model.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo")
data class TodoModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    var status: Boolean = false)