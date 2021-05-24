package com.aditprayogo.core.utils

import com.aditprayogo.core.data.remote.response.Bantuan

/**
 * Created by Aditiya Prayogo.
 */
object DataDummy {
    fun populateHistory() =
        mutableListOf(
            Bantuan("A", "Completed"),
            Bantuan("A", "Completed"),
            Bantuan("A", "Completed"),
            Bantuan("A", "Completed"),
            Bantuan("A", "Completed"),
            Bantuan("A", "Completed"),
        )
}