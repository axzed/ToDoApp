package com.example.todoapp.fragments.update

import android.os.Bundle
import android.view.*
import android.widget.EditText
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.todoapp.R
import com.example.todoapp.fragments.data.model.Priority
import com.example.todoapp.fragments.data.viewmodel.SharedViewModel


class UpdateFragment : Fragment() {

    // 用navArgs()获取传递过来的参数
    private val args by navArgs<UpdateFragmentArgs>()

    // 获取viewModel
    // 用来实现一些相同的逻辑 如给优先级上色
    private val mSharedViewModel by viewModels<SharedViewModel>()

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
        curPriorities.setSelection(parsePriority(args.currentItem.priority))
        curPriorities.onItemSelectedListener = mSharedViewModel.listener

        return view
    }

    // menu
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        // 1. 加载menu
        inflater.inflate(R.menu.update_fragment_menu, menu)
    }

    // parsePriority 用于解析优先级
    private fun parsePriority(priority: Priority): Int {
        return when (priority) {
            Priority.HIGH -> 0
            Priority.MEDIUM -> 1
            Priority.LOW -> 2
        }
    }

}