package com.example.todoapp.fragments.add

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.example.todoapp.R

class AddFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // 设置menu
        setHasOptionsMenu(true)

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add, container, false)
    }

    // menu
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        // 1. 加载menu
        inflater.inflate(R.menu.add_fragment_menu, menu)
    }

}