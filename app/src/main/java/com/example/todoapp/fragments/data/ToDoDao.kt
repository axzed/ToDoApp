package com.example.todoapp.fragments.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.todoapp.fragments.data.model.ToDoData

@Dao
interface ToDoDao {

    // 查询所有数据
    @Query("SELECT * FROM todo_table ORDER BY id ASC")
    fun getAllData(): LiveData<List<ToDoData>>

    // 插入数据
    // OnConflictStrategy.REPLACE: 如果有冲突，就替换
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertData(toDoData: ToDoData)

}