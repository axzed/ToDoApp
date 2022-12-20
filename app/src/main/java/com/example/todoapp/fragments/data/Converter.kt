package com.example.todoapp.fragments.data

import androidx.room.TypeConverter
import com.example.todoapp.fragments.data.model.Priority

class Converter {

    // 将Priority转换为Int
    @TypeConverter
    fun fromPriority(priority: Priority): String {
        return priority.name
    }

    // 将Int转换为Priority
    @TypeConverter
    fun toPriority(priority: String): Priority {
        return Priority.valueOf(priority)
    }

}