package com.aditprayogo.samana_user.presentation.detail_news

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.aditprayogo.core.data.remote.response.Article
import com.aditprayogo.core.utils.load
import com.aditprayogo.samana_user.R
import com.aditprayogo.samana_user.databinding.FragmentDetailNewsBinding
import com.aditprayogo.samana_user.presentation.news.NewsFragmentDirections

class DetailNewsFragment : Fragment() {

    private val binding: FragmentDetailNewsBinding by lazy {
        FragmentDetailNewsBinding.inflate(layoutInflater)
    }

    private val args: DetailNewsFragmentArgs by navArgs()

    private lateinit var article: Article

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
    }

    private fun initData() {
        with(binding) {
            article = args.article
            imgDetailNews.load(article.urlToImage)
            txtInformation.text = article.description
        }
    }

}