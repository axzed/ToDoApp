package com.example.todoapp.fragments.data

import androidx.room.Entity
import androidx.room.PrimaryKey

// 创建一个实体类
// 用@Entity注解
@Entity(tableName = "todo_table")
data class ToDoData(
    // 用@PrimaryKey注解
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var title: String,
    var priority: Priority,
    var description: String,
)