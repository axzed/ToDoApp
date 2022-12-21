package com.example.todoapp.fragments.list

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet.Layout
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.R
import com.example.todoapp.fragments.data.viewmodel.ToDoViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListFragment : Fragment() {

    private val mToDoViewModel: ToDoViewModel by viewModels()

    private val adapter: ListAdapter by lazy { ListAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        // 获取 recyclerview
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        // 设置recyclerview的适配器
        recyclerView.adapter = adapter
        // 设置recyclerview的布局管理器
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())

        // 获取viewModel
        mToDoViewModel.getAllData.observe(viewLifecycleOwner, Observer {
            // 更新adapter
            data ->
            adapter.setData(data)
        })

        // 设置按钮点击事件(添加)
        view.findViewById<FloatingActionButton>(R.id.addButton).setOnClickListener {
            // 跳转到添加页面
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }



        // 设置menu
        setHasOptionsMenu(true)

        return view
    }

    // 重写menu
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        // 1. 加载menu
        inflater.inflate(R.menu.list_fragment_menu, menu)
    }

    // 重写menu的点击事件
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // 1. 判断点击的是哪个item
        if (item.itemId == R.id.menu_delete_all) {
            confirmRemoval()
        }
        return super.onOptionsItemSelected(item)
    }

    // 删除所有数据
    // 弹出对话框提示用户是否删除
    private fun confirmRemoval() {
        // 1. 创建一个对话框
        val builder = androidx.appcompat.app.AlertDialog.Builder(requireContext())
        // 2. 设置对话框的标题
        builder.setTitle("删除所有?")
        // 3. 设置对话框的内容
        builder.setMessage("您确定要删除所有吗?")
        // 4. 设置对话框的按钮
        builder.setPositiveButton("确定") { _, _ ->
            // 5. 删除所有数据
            mToDoViewModel.deleteAll()
            // 6. 提示用户
            Toast.makeText(requireContext(), "完咯，全部数据都被删掉咯!", Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("取消") { _, _ -> }
        // 7. 显示对话框
        builder.create().show()
    }

}