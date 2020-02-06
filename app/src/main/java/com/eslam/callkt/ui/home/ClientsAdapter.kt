package com.eslam.callkt.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.eslam.callkt.R
import com.eslam.callkt.databinding.ClientRowItemBinding
import com.eslam.callkt.room.ClientEntity

class ClientsAdapter(val listener: ClickListener) :
    ListAdapter<ClientEntity, ClientsAdapter.ViewHolder>(DiffCall) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), listener)
    }

    companion object DiffCall : DiffUtil.ItemCallback<ClientEntity>() {
        override fun areItemsTheSame(oldItem: ClientEntity, newItem: ClientEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ClientEntity, newItem: ClientEntity): Boolean {
            return oldItem == newItem
        }
    }

    class ViewHolder(private val binding: ClientRowItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                return ViewHolder(
                    DataBindingUtil
                        .inflate(
                            LayoutInflater.from(parent.context),
                            R.layout.client_row_item,
                            parent,
                            false
                        )
                )
            }
        }

        fun bind(client: ClientEntity, listener: ClickListener) {
            binding.client = client
            binding.listener = listener
            binding.executePendingBindings()
        }
    }

    interface ClickListener {
        fun onRowClick(v: View, client: ClientEntity)
    }
}