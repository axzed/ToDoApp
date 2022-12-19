package com.example.todoapp.fragments.list

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet.Layout
import androidx.fragment.app.setFragmentResult
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.todoapp.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)
        // 设置按钮点击事件(添加)
        view.findViewById<FloatingActionButton>(R.id.addButton).setOnClickListener {
            // 跳转到添加页面
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        // 通过点击listLayout中的任意地方跳转到updateFragment
        view.findViewById<ConstraintLayout>(R.id.listLayout).setOnClickListener {
            // 1. 获取导航控制器
            // 2. 跳转到updateFragment
            findNavController().navigate(R.id.action_listFragment_to_updateFragment)
        }
        return view
    }

}