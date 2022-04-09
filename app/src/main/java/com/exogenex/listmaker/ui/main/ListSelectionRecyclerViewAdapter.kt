package com.exogenex.listmaker.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.exogenex.listmaker.databinding.ListSelectionViewHolderBinding
import com.exogenex.listmaker.models.TaskList

class ListSelectionRecyclerViewAdapter(val lists: MutableList<TaskList>, val clickListener: ListSelectionRecyclerViewClickListener)
    : RecyclerView.Adapter<ListSelectionViewHolder>()  {

    interface ListSelectionRecyclerViewClickListener { fun listItemClicked(list: TaskList) }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListSelectionViewHolder {
        val binding = ListSelectionViewHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListSelectionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListSelectionViewHolder, position: Int) {
        holder.binding.itemNumber.text = (position + 1).toString()
        holder.binding.itemString.text = lists[position].name
        holder.itemView.setOnClickListener { clickListener.listItemClicked(lists[position]) }
    }

    override fun getItemCount(): Int { return lists.size }

    fun listsUpdated() { notifyItemInserted(lists.size-1) }
}