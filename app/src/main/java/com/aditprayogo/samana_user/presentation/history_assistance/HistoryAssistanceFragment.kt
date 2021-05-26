package com.aditprayogo.samana_user.presentation.history_assistance

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.aditprayogo.core.utils.DataDummy
import com.aditprayogo.samana_user.R
import com.aditprayogo.samana_user.databinding.FragmentHistoryAssistanceBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryAssistanceFragment : Fragment() {

    private val binding: FragmentHistoryAssistanceBinding by lazy {
        FragmentHistoryAssistanceBinding.inflate(layoutInflater)
    }

    private val historyAssistanceAdapter: HistoryAssistanceAdapter by lazy {
        HistoryAssistanceAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        with(binding) {
            rvAssistance.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            rvAssistance.adapter = historyAssistanceAdapter
//            historyAssistanceAdapter.setData(DataDummy.populateHistory())
        }
    }

}