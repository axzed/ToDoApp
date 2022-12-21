package com.example.todoapp.fragments.list

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.R
import com.example.todoapp.databinding.FragmentListBinding
import com.example.todoapp.fragments.data.viewmodel.SharedViewModel
import com.example.todoapp.fragments.data.viewmodel.ToDoViewModel
import com.example.todoapp.fragments.list.adapter.ListAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListFragment : Fragment() {

    private val mToDoViewModel: ToDoViewModel by viewModels()

    private val adapter: ListAdapter by lazy { ListAdapter() }

    private val mSharedViewModel: SharedViewModel by viewModels()

    // FragmentListBinding
    // 生成的类名是根据布局文件名来的
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // Use Data binding
        _binding = FragmentListBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.mSharedViewModel = mSharedViewModel

        // Setup RecyclerView
        setupRecyclerView()

        // 获取viewModel
        // Observe the LiveData
        mToDoViewModel.getAllData.observe(viewLifecycleOwner, Observer {
            // 更新adapter
            data ->
            mSharedViewModel.checkIfDatabaseEmpty(data)
            adapter.setData(data)
        })

        // 设置menu
        setHasOptionsMenu(true)

        return binding.root
    }

    // setupRecyclerView 方法 (设置RecyclerView)
    // 初始化RecyclerView
    private fun setupRecyclerView() {
        // 获取 recyclerview
        val recyclerView = binding.recyclerView
        // 设置recyclerview的适配器
        recyclerView.adapter = adapter
        // 设置recyclerview的布局管理器
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())
    }

    // swipeToDelete 用于实现滑动删除
    private fun swipeToDelete(recyclerView: RecyclerView) {
        val swipeToDeleteCallback = object : SwipToDelete() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                // 获取当前的item
                val deletedItem = adapter.dataList[viewHolder.adapterPosition]
                // 删除item
                mToDoViewModel.deleteItem(deletedItem)
                // 提示
                Toast.makeText(requireContext(), "'${deletedItem.title}'删除成功", Toast.LENGTH_SHORT).show()
            }
        }
        // 创建ItemTouchHelper
        val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallback)
        // 绑定recyclerview
        itemTouchHelper.attachToRecyclerView(recyclerView)
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

    // 重写onDestroyView方法
    // 释放binding
    // 防止内存泄漏 (very important)
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}