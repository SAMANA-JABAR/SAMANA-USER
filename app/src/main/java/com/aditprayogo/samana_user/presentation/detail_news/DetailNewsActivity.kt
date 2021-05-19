package com.aditprayogo.samana_user.presentation.detail_news

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.aditprayogo.core.data.remote.response.Article
import com.aditprayogo.core.utils.load
import com.aditprayogo.samana_user.R
import com.aditprayogo.samana_user.databinding.ActivityDetailNewsBinding

class DetailNewsActivity : AppCompatActivity() {

    private val binding: ActivityDetailNewsBinding by lazy {
        ActivityDetailNewsBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        loadData()
        initToolbar()
    }

    private fun initToolbar() {
        supportActionBar?.apply {
            elevation = 0f
            setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun loadData() {
        with(binding) {
            val data = intent.getParcelableExtra<Article>(EXTRA_NEWS)
            data?.let {
                imgDetailNews.load(data.urlToImage)
                txtInformation.text = data.description
            }
        }
    }

    companion object {
        const val EXTRA_NEWS = "extra_news"
    }
}