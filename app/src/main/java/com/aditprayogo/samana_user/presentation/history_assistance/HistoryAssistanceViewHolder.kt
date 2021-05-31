package com.aditprayogo.samana_user.presentation.history_assistance

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.aditprayogo.core.domain.model.HistoryData
import com.aditprayogo.samana_user.databinding.ItemRowAssistanceBinding

/**
 * Created by Aditiya Prayogo.
 */
class HistoryAssistanceViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

    private val binding : ItemRowAssistanceBinding = ItemRowAssistanceBinding.bind(itemView)

    fun bind(data : HistoryData) {
        with(binding) {
            txtAssistance.text = data.jenis
        }
    }

}