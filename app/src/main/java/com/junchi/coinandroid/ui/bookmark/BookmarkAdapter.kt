package com.junchi.coinandroid.ui.bookmark

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.junchi.coinandroid.database.entity.BookmarkEntity
import com.junchi.coinandroid.databinding.ItemBookmarkBinding
import com.junchi.coinandroid.ui.common.DeleteClickListener
import com.junchi.coinandroid.ui.common.DetailClickListener

class BookmarkAdapter(private val clickListener: DetailClickListener,
                      private val deleteClickListener: DeleteClickListener): ListAdapter<BookmarkEntity, BookmarkAdapter.BookmarkViewHolder>(BookmarkDiffCallback()) {

    inner class BookmarkViewHolder(private val binding: ItemBookmarkBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(bookmark: BookmarkEntity){
            binding.clickListener = clickListener
            binding.delete = deleteClickListener
            binding.data = bookmark
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkViewHolder {
        val binding = ItemBookmarkBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return BookmarkViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookmarkViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class BookmarkDiffCallback : DiffUtil.ItemCallback<BookmarkEntity>(){
    override fun areItemsTheSame(oldItem: BookmarkEntity, newItem: BookmarkEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: BookmarkEntity, newItem: BookmarkEntity): Boolean {
        return  oldItem == newItem
    }
}