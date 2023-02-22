package com.junchi.coinandroid.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.junchi.coinandroid.databinding.ItemCoinInfoBinding
import com.junchi.coinandroid.model.CoinData

class CoinInfoAdapter:ListAdapter<CoinData, CoinInfoAdapter.CoinInfoViewHolder>(CoinInfoDiffCallback()) {
    class CoinInfoViewHolder(private val binding: ItemCoinInfoBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(coinInfo: CoinData){
            binding.data = coinInfo
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinInfoViewHolder {
        val binding = ItemCoinInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CoinInfoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CoinInfoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class CoinInfoDiffCallback : DiffUtil.ItemCallback<CoinData>() {
    override fun areItemsTheSame(oldItem: CoinData, newItem: CoinData): Boolean {
        return oldItem.date == newItem.date
    }

    override fun areContentsTheSame(oldItem: CoinData, newItem: CoinData): Boolean {
        return oldItem == newItem
    }

}