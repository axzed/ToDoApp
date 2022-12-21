package com.example.todoapp.fragments.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.todoapp.fragments.data.ToDoDatabase
import com.example.todoapp.fragments.data.model.ToDoData
import com.example.todoapp.fragments.data.repository.ToDoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ToDoViewModel(application: Application) : AndroidViewModel(application) {

    // 获取数据库的实例
    private val toDoDao = ToDoDatabase.getDatabase(application).toDoDao()
    // 获取数据库的接口
    private val repository:  ToDoRepository
    // 获取数据库中的数据
    val getAllData: LiveData<List<ToDoData>>

    // 用init初始化
    init {
        // 用repository初始化
        repository = ToDoRepository(toDoDao)
        // 用getAllData初始化
        getAllData = repository.getAllData
    }

    // 插入数据
    fun insertData(toDoData: ToDoData) {
        // 用viewModelScope.launch{}启动协程
        viewModelScope.launch(Dispatchers.IO) {
            // 用repository.insertData()插入数据
            repository.insertData(toDoData)
        }
    }

    // 更新数据
    fun updateData(toDoData: ToDoData) {
        // 用viewModelScope.launch{}启动协程
        viewModelScope.launch(Dispatchers.IO) {
            // 用repository.updateData()更新数据
            repository.updateData(toDoData)
        }
    }

    // 删除数据
    fun deleteItem(toDoData: ToDoData) {
        // 用viewModelScope.launch{}启动协程
        viewModelScope.launch(Dispatchers.IO) {
            // 用repository.deleteItem()删除数据
            repository.deleteItem(toDoData)
        }
    }

}