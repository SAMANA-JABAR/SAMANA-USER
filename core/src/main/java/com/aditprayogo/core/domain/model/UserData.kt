package com.aditprayogo.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Created by Aditiya Prayogo.
 */
@Parcelize
data class UserData(
     val nik : String?,
     val password : String?,
     val nama : String?
) : Parcelable