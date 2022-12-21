package com.example.todoapp.fragments.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.todoapp.fragments.data.model.Priority
import kotlinx.parcelize.Parcelize

// 创建一个实体类
// 用@Entity注解
@Entity(tableName = "todo_table")
@Parcelize
data class ToDoData(
    // 用@PrimaryKey注解
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var title: String,
    var priority: Priority,
    var description: String,
): Parcelable