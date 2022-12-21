package com.example.todoapp.fragments.list.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.R
import com.example.todoapp.fragments.data.model.Priority
import com.example.todoapp.fragments.data.model.ToDoData
import com.example.todoapp.fragments.list.ListFragmentDirections

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    var dataList = emptyList<ToDoData>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        // 获取itemView中的控件
        val title_txt = itemView.findViewById<TextView>(R.id.title_txt)
        val description_txt = itemView.findViewById<TextView>(R.id.description_txt)
        val priority_indicator = itemView.findViewById<CardView>(R.id.priority_indicator)
        val row_background = itemView.findViewById<ConstraintLayout>(R.id.row_background)
    }

    // 创建viewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        // 1. 创建view
        // 2. 创建viewHolder
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_layout, parent, false)
        // 3. 返回viewHolder
        return MyViewHolder(view)
    }

    // 绑定数据
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // 1. 获取数据
        holder.title_txt.text = dataList[position].title
        holder.description_txt.text = dataList[position].description
        holder.row_background.setOnClickListener {
            // 跳转到更新页面 并传递数据
            val action =
                ListFragmentDirections.actionListFragmentToUpdateFragment(dataList[position])
            // 点击row_background跳转到updateFragment
            holder.row_background.findNavController().navigate(action)
        }

        val priority = dataList[position].priority
        when(priority) {
            Priority.HIGH -> holder.priority_indicator.setCardBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.red))
            Priority.MEDIUM -> holder.priority_indicator.setCardBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.yellow))
            Priority.LOW -> holder.priority_indicator.setCardBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.green))
        }
    }

    // 获取数据的数量
    override fun getItemCount(): Int {
        return dataList.size
    }

    // 更新数据
    fun setData(toDoData: List<ToDoData>) {
        this.dataList = toDoData
        notifyDataSetChanged()
    }

}