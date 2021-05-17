package com.aditprayogo.samana_user.presentation.news

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.aditprayogo.core.data.remote.response.Article
import com.aditprayogo.core.state.LoaderState
import com.aditprayogo.core.utils.setGone
import com.aditprayogo.core.utils.setVisible
import com.aditprayogo.samana_user.databinding.FragmentNewsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsFragment : Fragment() {

    private val binding : FragmentNewsBinding by lazy {
        FragmentNewsBinding.inflate(layoutInflater)
    }

    private val dataArticles = mutableListOf<Article>()

    private val newsAdapter : NewsAdapter by lazy {
        NewsAdapter()
    }

    private val newsViewModel by viewModels<NewsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        initAdapters()
    }

    private fun initAdapters() {
        binding.rvNews.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = newsAdapter
        }
    }

    private fun initObservers() {
        with(newsViewModel) {
            resultIndonesianNewsFromApi.observe(viewLifecycleOwner, {
                handleArticleData(it)
            })
            state.observe(viewLifecycleOwner, {
                handleLoaderState(it)
            })
        }
    }

    private fun handleLoaderState(it: LoaderState) {
        with(binding) {
            if (it is LoaderState.ShowLoading) {
                rvNews.setGone()
                baseLoading.root.setVisible()
            } else {
                rvNews.setVisible()
                baseLoading.root.setGone()
            }
        }
    }

    private fun handleArticleData(data: List<Article>) {
        dataArticles.clear()
        dataArticles.addAll(data)
        newsAdapter.setNewsData(dataArticles)
    }


}