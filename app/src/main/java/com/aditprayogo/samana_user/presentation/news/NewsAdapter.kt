package com.aditprayogo.samana_user.presentation.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aditprayogo.core.data.remote.response.Article
import com.aditprayogo.samana_user.R

/**
 * Created by Aditiya Prayogo.
 */
class NewsAdapter : RecyclerView.Adapter<NewsViewHolder>() {

    private var articles = mutableListOf<Article>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_row_news, parent, false)
        )
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(articles[position])
    }

    override fun getItemCount(): Int = articles.size

    fun setNewsData(data : MutableList<Article>) {
        this.articles = data
        notifyDataSetChanged()
    }
}