package com.example.todoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.TaskStackBuilder
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 设置标题栏
        // 1. 获取导航控制器
        // 2. 设置标题栏
        // 3. 设置标题栏的返回按钮
        setupActionBarWithNavController(findNavController(R.id.navHostFragment))
    }

    // 重写返回按钮
    // 1. 获取导航控制器
    // 2. 调用导航控制器的navigateUp方法
    // 3. 返回true
    override fun onSupportNavigateUp(): Boolean {
        // 返回上一个页面
        val navController = findNavController(R.id.navHostFragment)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}