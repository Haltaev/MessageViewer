package com.example.messageviewer.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.messageviewer.R
import com.example.messageviewer.data.api.model.Message
import com.example.messageviewer.databinding.ItemMessageBinding

class HomeAdapter : ListAdapter<Message, RecyclerView.ViewHolder>(diffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemBinding = ItemMessageBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(getItem(position))
    }

    fun removeItem(position: Int) {
        val newList = arrayListOf<Message>()
        newList.addAll(currentList)
        newList.removeAt(position)
        submitList(newList)
    }

    fun restoreItem(item: Message, position: Int) {
        val newList = arrayListOf<Message>()
        newList.addAll(currentList)
        newList.add(position, item)
        submitList(newList)
    }

    inner class ViewHolder(val itemBinding: ItemMessageBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(item: Message) {
            itemBinding.apply {
                tvUserId.text = itemView.context.resources.getString(R.string.user_id, item.userId)
                tvTitle.text = item.title
                tvBody.text = item.body
            }
        }
    }

    companion object {
        @JvmStatic
        fun diffCallBack(): DiffUtil.ItemCallback<Message> =
            object : DiffUtil.ItemCallback<Message>() {
                override fun areItemsTheSame(
                    oldItem: Message,
                    newItem: Message
                ): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(
                    oldItem: Message,
                    newItem: Message
                ): Boolean {
                    return newItem.userId == oldItem.userId
                            && newItem.title == oldItem.title
                            && newItem.body == oldItem.body
                }
            }
    }
}