package com.junchi.coinandroid.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.junchi.coinandroid.databinding.ItemDetailHistoryBinding
import com.junchi.coinandroid.model.HistoryData

class CoinHistoryAdapter: ListAdapter<HistoryData, CoinHistoryAdapter.HistoryDataViewHolder>(HistoryDataDiff()) {

    class HistoryDataViewHolder(private val binding: ItemDetailHistoryBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(historyData: HistoryData){
            binding.data = historyData
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryDataViewHolder {
        val binding = ItemDetailHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HistoryDataViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoryDataViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class HistoryDataDiff : DiffUtil.ItemCallback<HistoryData>(){
    override fun areItemsTheSame(oldItem: HistoryData, newItem: HistoryData): Boolean {
        return oldItem.price == newItem.price
    }

    override fun areContentsTheSame(oldItem: HistoryData, newItem: HistoryData): Boolean {
        return oldItem == newItem
    }
}