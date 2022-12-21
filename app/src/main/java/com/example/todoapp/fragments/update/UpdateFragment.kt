package com.example.todoapp.fragments.update

import android.os.Bundle
import android.view.*
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.todoapp.R
import com.example.todoapp.fragments.data.model.Priority
import com.example.todoapp.fragments.data.model.ToDoData
import com.example.todoapp.fragments.data.viewmodel.SharedViewModel
import com.example.todoapp.fragments.data.viewmodel.ToDoViewModel


class UpdateFragment : Fragment() {

    // 用navArgs()获取传递过来的参数
    private val args by navArgs<UpdateFragmentArgs>()

    // 获取viewModel
    // 用来实现一些相同的逻辑 如给优先级上色
    private val mSharedViewModel by viewModels<SharedViewModel>()
    // 获取viewModel
    // 用来实现一些相同的逻辑 如更新数据
    private val mToDoViewModel by viewModels<ToDoViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        // set menu
        setHasOptionsMenu(true)

        // find the view you need to manipulate
        // find cur_title_et
        val curTitle = view.findViewById<EditText>(R.id.cur_title_et)
        // find cur_description_et
        val curDescription = view.findViewById<EditText>(R.id.cur_description_et)
        // find cur_priorities_spinner
        val curPriorities = view.findViewById<Spinner>(R.id.cur_priorities_spinner)

        // set cur_title_et
        curTitle.setText(args.currentItem.title)
        // set cur_description_et
        curDescription.setText(args.currentItem.description)
        // set cur_priorities_spinner
        curPriorities.setSelection(mSharedViewModel.parsePriorityToInt(args.currentItem.priority))
        // set cur_priorities_spinner color
        curPriorities.onItemSelectedListener = mSharedViewModel.listener

        return view
    }

    // menu
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        // 1. 加载menu
        inflater.inflate(R.menu.update_fragment_menu, menu)
    }

    // menu item click
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // 1. 判断点击的是哪个item
        if (item.itemId == R.id.menu_save) {
            // 2. 保存数据
            updateItem()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun updateItem() {
        // 1. 获取view
        val curTitle = view?.findViewById<EditText>(R.id.cur_title_et)
        val curDescription = view?.findViewById<EditText>(R.id.cur_description_et)
        val curPriorities = view?.findViewById<Spinner>(R.id.cur_priorities_spinner)

        // 2. 获取数据
        val mTitle = curTitle?.text.toString()
        val mDescription = curDescription?.text.toString()
        val getPriority = curPriorities?.selectedItem.toString()

        // 3. 获取优先级
        val validation = mSharedViewModel.verifyDataFromUser(mTitle, mDescription)
        val priority = mSharedViewModel.parsePriority(getPriority)

        // 4. 更新数据
        if (validation) {
            // 封装更新了的数据
            val updatedItem = ToDoData(
                args.currentItem.id,
                mTitle,
                priority,
                mDescription
            )
            // 更新数据
            mToDoViewModel.updateData(updatedItem)
            // 提示
            Toast.makeText(requireContext(), "更新成功了!!!", Toast.LENGTH_SHORT).show()
            // navigate back to the list layout
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        } else {
            // 提示
            Toast.makeText(requireContext(), "请填写完整的数据!!!", Toast.LENGTH_SHORT).show()
        }

    }


}