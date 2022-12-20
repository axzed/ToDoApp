package com.example.todoapp.fragments.data.repository

import androidx.lifecycle.LiveData
import com.example.todoapp.fragments.data.ToDoDao
import com.example.todoapp.fragments.data.model.ToDoData

// 用于访问数据库的接口
class ToDoRepository(private val toDoDao: ToDoDao) {

    val getAllData: LiveData<List<ToDoData>> = toDoDao.getAllData()

    suspend fun insertData(toDoData: ToDoData) {
        toDoDao.insertData(toDoData)
    }

}