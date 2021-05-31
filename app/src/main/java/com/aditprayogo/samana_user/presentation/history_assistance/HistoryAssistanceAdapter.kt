package com.aditprayogo.samana_user.presentation.history_assistance

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aditprayogo.core.domain.model.HistoryData
import com.aditprayogo.samana_user.R

/**
 * Created by Aditiya Prayogo.
 */
class HistoryAssistanceAdapter : RecyclerView.Adapter<HistoryAssistanceViewHolder>() {

    private var historyData = mutableListOf<HistoryData>()

    fun setData(item : MutableList<HistoryData>) {
        this.historyData = item
        notifyDataSetChanged()
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryAssistanceViewHolder {
        return HistoryAssistanceViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_row_assistance, parent, false)
        )
    }

    override fun onBindViewHolder(holder: HistoryAssistanceViewHolder, position: Int) {
        holder.bind(historyData[position])
    }

    override fun getItemCount(): Int = historyData.size
}