package com.aditprayogo.samana_user.presentation.news

import android.view.View
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.aditprayogo.core.data.remote.response.Article
import com.aditprayogo.core.utils.load
import com.aditprayogo.samana_user.R
import com.aditprayogo.samana_user.databinding.ItemRowNewsBinding

/**
 * Created by Aditiya Prayogo.
 */
class NewsViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

    private val binding : ItemRowNewsBinding = ItemRowNewsBinding.bind(itemView)

    fun bind(data : Article) {
        with(itemView) {
            binding.apply {
                ivItemImage.load(data.urlToImage)
                tvItemTitle.text = data.title
                tvItemSubtitle.text = data.author ?: context.getString(R.string.wawan)
            }
            setOnClickListener {
                val directions = NewsFragmentDirections.actionNewsFragmentToDetailNewsFragment(data, data.title!!)
                findNavController().navigate(directions)
            }
        }

    }

}