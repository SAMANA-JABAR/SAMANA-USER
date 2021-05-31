package com.aditprayogo.samana_user.presentation.history_assistance

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.aditprayogo.core.data.UserPreferences
import com.aditprayogo.core.domain.model.HistoryData
import com.aditprayogo.core.state.LoaderState
import com.aditprayogo.core.utils.DataDummy
import com.aditprayogo.core.utils.setGone
import com.aditprayogo.core.utils.setVisible
import com.aditprayogo.samana_user.R
import com.aditprayogo.samana_user.databinding.FragmentHistoryAssistanceBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HistoryAssistanceFragment : Fragment() {

    private val binding: FragmentHistoryAssistanceBinding by lazy {
        FragmentHistoryAssistanceBinding.inflate(layoutInflater)
    }

    private val historyAssistanceAdapter: HistoryAssistanceAdapter by lazy {
        HistoryAssistanceAdapter()
    }

    private val historyAssistanceViewModel: HistoryAssistanceViewModel by viewModels()

    private var historyData = mutableListOf<HistoryData>()

    @Inject
    lateinit var userPreferences: UserPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initDataNik()
        initRecyclerView()
        initObservers()
    }

    private fun initDataNik() {
        lifecycleScope.launch {
            userPreferences.nik.asLiveData().observe(viewLifecycleOwner, {
                it?.let {
                    historyAssistanceViewModel.getHistoryBansos(it)
                }
            })
        }
    }

    private fun initObservers() {
        with(historyAssistanceViewModel) {
            state.observe(viewLifecycleOwner, {
                handleLoaderState(it)
            })

            historyData.observe(viewLifecycleOwner, {
                handleHistoryData(it)
            })
        }
    }

    private fun handleLoaderState(it: LoaderState) {
        with(binding) {
            if (it is LoaderState.ShowLoading) {
                baseLoading.root.setVisible()
                rvAssistance.setGone()
            } else {
                baseLoading.root.setGone()
                rvAssistance.setVisible()
            }
        }
    }

    private fun handleHistoryData(data: List<HistoryData>) {
        historyData.clear()
        historyData.addAll(data)
        historyAssistanceAdapter.setData(historyData)
    }

    private fun initRecyclerView() {
        with(binding) {
            rvAssistance.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            rvAssistance.adapter = historyAssistanceAdapter
        }
    }

}