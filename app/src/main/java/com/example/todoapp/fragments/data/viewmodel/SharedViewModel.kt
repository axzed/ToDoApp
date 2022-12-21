package com.example.todoapp.fragments.data.viewmodel

import android.app.Application
import android.graphics.Color
import android.text.TextUtils
import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import com.example.todoapp.R
import com.example.todoapp.fragments.data.model.Priority

class SharedViewModel(application: Application): AndroidViewModel(application) {

    // Spinner selected item listener
    // This will be called when the user selects an item from the spinner
    val listener: AdapterView.OnItemSelectedListener = object : AdapterView.OnItemSelectedListener {

        // This function is used to verify data before saving it to the database
        override fun onNothingSelected(p0: AdapterView<*>?) {}

        // This function is used to verify data before saving it to the database
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            when(position){
                0 -> { (parent?.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(application, R.color.red)) }
                1 -> { (parent?.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(application, R.color.yellow)) }
                2 -> { (parent?.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(application, R.color.green)) }
            }
        }

    }

    // 验证数据
    fun verifyDataFromUser(title: String, description: String): Boolean {
        // 5. 判断title和description是否为空
        return if (TextUtils.isEmpty(title) || TextUtils.isEmpty(description)) {
            false
            // 6. 如果不为空，就返回true
        } else !(title.isEmpty() || description.isEmpty())
    }

    // parse priority string in to enum
    fun parsePriority(priority: String): Priority {
        // 判断优先级
        return when (priority) {
            "高优先级" -> {
                Priority.HIGH
            }
            "中优先级" -> {
                Priority.MEDIUM
            }
            "低优先级" -> {
                Priority.LOW
            }
            else -> Priority.LOW
        }
    }


    // parsePriority 用于解析优先级
    fun parsePriorityToInt(priority: Priority): Int {
        return when (priority) {
            Priority.HIGH -> 0
            Priority.MEDIUM -> 1
            Priority.LOW -> 2
        }
    }

}