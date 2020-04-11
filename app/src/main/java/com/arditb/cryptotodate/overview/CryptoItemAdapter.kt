package com.arditb.cryptotodate.overview


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.arditb.cryptotodate.databinding.CryptoItemBinding
import com.arditb.cryptotodate.network.CryptoItem

class CryptoItemAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<CryptoItem, CryptoItemAdapter.CryptoItemViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoItemViewHolder {
        return CryptoItemViewHolder(CryptoItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: CryptoItemViewHolder, position: Int) {
        val cryptoItem = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(cryptoItem)
        }
        holder.bind(cryptoItem)
    }


    companion object DiffCallback : DiffUtil.ItemCallback<CryptoItem>() {
        override fun areItemsTheSame(oldItem: CryptoItem, newItem: CryptoItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: CryptoItem, newItem: CryptoItem): Boolean {
            return oldItem.id == newItem.id
        }
    }

    class CryptoItemViewHolder(private var binding: CryptoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(cryptoItem: CryptoItem) {
            binding.crypto = cryptoItem
            binding.executePendingBindings()
        }
    }

    class OnClickListener(val clickListener: (cryptoItem: CryptoItem) -> Unit) {
        fun onClick(cryptoItem: CryptoItem) = clickListener(cryptoItem)
    }
}

