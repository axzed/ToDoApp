package com.example.todoapp.fragments.add

import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.todoapp.R
import com.example.todoapp.fragments.data.model.Priority
import com.example.todoapp.fragments.data.model.ToDoData
import com.example.todoapp.fragments.data.viewmodel.ToDoViewModel

class AddFragment : Fragment() {

    private val mToDoViewModel: ToDoViewModel by viewModels()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)
        setHasOptionsMenu(true)
        return view

    }

    // menu
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        // 1. 加载menu
        inflater.inflate(R.menu.add_fragment_menu, menu)
    }

    // onOptionsItemSelected
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // 2. 判断item
        if (item.itemId == R.id.menu_add) {
            // 3. 保存数据
            insertDataToDb()
        }
        return super.onOptionsItemSelected(item)
    }

    // 保存数据
    private fun insertDataToDb() {

        // 获取title_et这个view
        val title_et = view?.findViewById<EditText>(R.id.title_et)
        // 获取priority_spinner这个view
        val priority_spinner = view?.findViewById<Spinner>(R.id.priorities_spinner)
        // 获取description_et这个view
        val description_et = view?.findViewById<EditText>(R.id.description_et)

        val mTitle = title_et?.text.toString()
        val mPriority = priority_spinner?.selectedItem.toString()
        val mDescription = description_et?.text.toString()

        // 判断数据是否为空
        val validation = verifyDataFromUser(mTitle, mDescription)
        if (validation) {
            // Insert Data to Database
            // 创建一个实体类
            val newData = ToDoData(
                0,
                mTitle,
                parsePriority(mPriority),
                mDescription
            )

            // 用mToDoViewModel.insertData()插入数据
            mToDoViewModel.insertData(newData)
            // 提示成功
            Toast.makeText(requireContext(), "添加成功!!!", Toast.LENGTH_SHORT).show()
            // 返回到列表查看
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        } else {
            // 提示失败
            Toast.makeText(requireContext(), "请确认所有字段填写完成!!!", Toast.LENGTH_SHORT).show()
        }
    }

    // 验证数据
    private fun verifyDataFromUser(title: String, description: String): Boolean {
        // 5. 判断title和description是否为空
        return if (TextUtils.isEmpty(title) || TextUtils.isEmpty(description)) {
            false
            // 6. 如果不为空，就返回true
        } else !(title.isEmpty() || description.isEmpty())
    }

    // parse priority string in to enum
    private fun parsePriority(priority: String): Priority {
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

}