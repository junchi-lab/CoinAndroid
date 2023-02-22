package com.junchi.coinandroid.ui.status

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.junchi.coinandroid.databinding.ItemStatusBinding
import com.junchi.coinandroid.model.CoinStatus
import com.junchi.coinandroid.ui.common.DetailClickListener

class StatusAdapter(private val clickListener: DetailClickListener,
                    private val viewModel: StatusViewModel): ListAdapter<CoinStatus, StatusAdapter.CoinStatusViewHolder>(StatusDiffCallback()) {

    inner class CoinStatusViewHolder(private val binding: ItemStatusBinding): RecyclerView.ViewHolder (binding.root){

        fun bind(coinStatus: CoinStatus){
            binding.clickListener = clickListener
            binding.viewModel = viewModel
            binding.coinStatus = coinStatus
            binding.executePendingBindings()

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinStatusViewHolder {
        val binding = ItemStatusBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CoinStatusViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CoinStatusViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class StatusDiffCallback : DiffUtil.ItemCallback<CoinStatus>(){
    override fun areItemsTheSame(oldItem: CoinStatus, newItem: CoinStatus): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: CoinStatus, newItem: CoinStatus): Boolean {
        return  oldItem == newItem
    }
}