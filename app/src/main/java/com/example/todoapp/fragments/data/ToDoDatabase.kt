package com.example.todoapp.fragments.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.todoapp.fragments.data.model.ToDoData

// 用@Database注解 传入实体类和版本号生成数据库
// 用@TypeConverters注解 传入Converter类
@Database(entities = [ToDoData::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class ToDoDatabase: RoomDatabase() {
    // 用abstract修饰，返回一个ToDoDao
    abstract fun toDoDao(): ToDoDao

    companion object {
        @Volatile
        private var INSTANCE: ToDoDatabase? = null

        // 用synchronized修饰，保证线程安全
        // 用fun修饰，返回一个ToDoDatabase
        // 用context: Context作为参数
        fun getDatabase(context: Context): ToDoDatabase {
            val tempInstance = INSTANCE
            // 如果tempInstance不为空，就返回tempInstance
            if (tempInstance != null) {
                return tempInstance
            }
            // 如果tempInstance为空，就创建一个数据库
            synchronized(this) {
                // 用Room.databaseBuilder创建一个数据库
                val instance = Room.databaseBuilder(
                    // 传入context
                    context.applicationContext,
                    // 传入ToDoDatabase::class.java
                    ToDoDatabase::class.java,
                    // 传入数据库名
                    "todo_database"
                ).build()
                // 把instance赋值给INSTANCE
                INSTANCE = instance
                return instance
            }
        }

    }
}