package com.example.workoutapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.workoutapp.databinding.ActivityHistoryBinding
import com.example.workoutapp.databinding.RvListItemHistoryBinding

class HistoryAdaptor(private val historyList: ArrayList<HistoryEntity>) : RecyclerView.Adapter<HistoryAdaptor.ViewHolder>() {

    class ViewHolder(binding: RvListItemHistoryBinding) : RecyclerView.ViewHolder(binding.root) {
        val tvHistoryDate = binding.tvHistoryListItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RvListItemHistoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemView = holder.tvHistoryDate
        val currentItem = historyList[position]
        itemView.text = currentItem.Date

    }

    override fun getItemCount(): Int {
        return historyList.size
    }
}