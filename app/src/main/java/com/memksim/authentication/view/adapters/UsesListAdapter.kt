package com.memksim.authentication.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.memksim.authentication.databinding.UsesListItemBinding

class UsesListAdapter(private val usesList: List<String>): RecyclerView.Adapter<UsesListAdapter.UsesListViewHolder>() {

    class UsesListViewHolder(val binding: UsesListItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsesListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = UsesListItemBinding.inflate(inflater, parent, false)

        return UsesListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UsesListViewHolder, position: Int) {
        holder.binding.serviceNameText.text = usesList[position]
    }

    override fun getItemCount(): Int {
        return usesList.size
    }

}