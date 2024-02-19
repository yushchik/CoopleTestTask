package com.example.coopletesttask.presentation.searchCity

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.coopletesttask.data.model.db.CityTable
import com.example.coopletesttask.databinding.ItemCityBinding

class CityListAdapter(
    private val onClick: (CityTable) -> Unit
) : ListAdapter<CityTable, CityListAdapter.CityListViewHolder>(CityListDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCityBinding.inflate(inflater, parent, false)
        return CityListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CityListViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.itemView.setOnClickListener { onClick(item) }
    }

    class CityListViewHolder(private val binding: ItemCityBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CityTable) {
            binding.tvTitle.text = item.name
        }
    }
}

private class CityListDiffCallback : DiffUtil.ItemCallback<CityTable>() {

    override fun areItemsTheSame(oldItem: CityTable, newItem: CityTable): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(
        oldItem: CityTable,
        newItem: CityTable
    ): Boolean {
        return oldItem == newItem
    }
}
