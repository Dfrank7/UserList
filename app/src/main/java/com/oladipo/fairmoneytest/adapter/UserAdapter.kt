package com.oladipo.fairmoneytest.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.oladipo.fairmoneytest.databinding.ListItemBinding
import com.oladipo.fairmoneytest.model.Data
import com.oladipo.fairmoneytest.model.User
import com.squareup.picasso.Picasso

class UserAdapter(val clickListener: UserListener) : ListAdapter<Data, RecyclerView.ViewHolder>(DataDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is ViewHolder-> {
                val data = getItem(position)
                holder.bind(data, clickListener)
            }
        }
    }

    class DataDiffCallback : DiffUtil.ItemCallback<Data>() {
        override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem == newItem
        }

    }

    class ViewHolder private constructor(val binding: ListItemBinding, val context: Context):RecyclerView.ViewHolder(binding.root){
        fun bind(data: Data, listener: UserListener){
            binding.data = data
            binding.clickListener = listener
            Picasso.with(context).load(data.picture).into(binding.profileImage)
            binding.executePendingBindings()
        }

        companion object{
            fun from(parent: ViewGroup): ViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding, parent.context)
            }
        }
    }

    class UserListener(val clickListener: (userId: Data) -> Unit){
        fun onClick(user: Data) = clickListener(user)
    }



}