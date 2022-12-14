package com.example.todoapp.fragments.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
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

    // 更新数据
    @Update
    suspend fun updateData(toDoData: ToDoData)

    // 删除数据
    @Delete
    suspend fun deleteItem(toDoData: ToDoData)

    // 删除全部数据
    @Query("DELETE FROM todo_table")
    suspend fun deleteAll()

}