package com.junchi.coinandroid.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.junchi.coinandroid.databinding.ItemHomeRvCoinBinding
import com.junchi.coinandroid.model.Coin
import com.junchi.coinandroid.ui.common.DetailClickListener

class HomeCoinListAdapter(private val clickListener: DetailClickListener, private val viewModel: HomeViewModel) : ListAdapter<Coin, HomeCoinListAdapter.HomeCoinListViewHolder>(CoinListDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeCoinListViewHolder {
        return HomeCoinListViewHolder(ItemHomeRvCoinBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: HomeCoinListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class HomeCoinListViewHolder(private val binding: ItemHomeRvCoinBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(coin: Coin) {
            binding.clickListener = clickListener
            binding.coin = coin
            binding.viewModel = viewModel
            binding.executePendingBindings()
        }
    }
}

class CoinListDiffCallback : DiffUtil.ItemCallback<Coin>() {
    override fun areItemsTheSame(oldItem: Coin, newItem: Coin): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Coin, newItem: Coin): Boolean {
        return oldItem == newItem
    }

}