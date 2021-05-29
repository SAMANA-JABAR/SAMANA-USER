package com.aditprayogo.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Created by Aditiya Prayogo.
 */
@Parcelize
data class PasswordData(
    val status : String?
) : Parcelable