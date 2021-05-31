package com.aditprayogo.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Created by Aditiya Prayogo.
 */
@Parcelize
data class InputDataBantuan(
    var nik : String? = null,
    var nama : String? = null,
    var tgl_lahir : String? = null,
    var tanggungan : String? = null,
    var pendidikan : String? = null,
    var profesi : String? = null,
    var status : String? = null,
    var gaji : String? = null,
    var kota : String? = null,
    var kecamatan : String? = null,
    var kelurahan : String? = null,
    var rt : String? = null,
    var rw : String? = null,
    var alamat : String? = null,
    var bantuan : String? = null,
    var tahap : String? = null,
    var kesehatan : String? = null,
    var atap : String? = null,
    var dinding : String? = null,
    var lantai : String? = null,
    var penerangan : String? = null,
    var air : String? = null,
    var luas_rumah : String? = null,
) : Parcelable