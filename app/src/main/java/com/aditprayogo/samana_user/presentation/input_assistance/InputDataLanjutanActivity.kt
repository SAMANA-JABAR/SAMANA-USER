package com.aditprayogo.samana_user.presentation.input_assistance

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import com.aditprayogo.core.data.UserPreferences
import com.aditprayogo.core.domain.model.InputData
import com.aditprayogo.core.domain.model.InputDataBantuan
import com.aditprayogo.core.state.LoaderState
import com.aditprayogo.core.utils.*
import com.aditprayogo.samana_user.R
import com.aditprayogo.samana_user.databinding.ActivityInputDataLanjutanBinding
import com.aditprayogo.samana_user.presentation.home.HomeActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.NonCancellable.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class InputDataLanjutanActivity : AppCompatActivity() {

    private val binding: ActivityInputDataLanjutanBinding by lazy {
        ActivityInputDataLanjutanBinding.inflate(layoutInflater)
    }

    private val inputAssistanceViewModel : InputAssistanceViewModel by viewModels()

    @Inject
    lateinit var userPreferences: UserPreferences

    private var data: InputDataBantuan? = null

    private var nik: String? = null
    private var rt: String? = null
    private var rw: String? = null
    private var alamat: String? = null
    private var bantuan: String? = null
    private var tahap: String? = null

    private var kesehatan: String? = null
    private var atap: String? = null
    private var dinding: String? = null
    private var lantai: String? = null
    private var penerangan: String? = null
    private var air: String? = null
    private var luasRumah: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initObservers()
        initDataFromIntent()
        initUi()
    }

    private fun initDataFromIntent() {
        data = intent?.getParcelableExtra(INPUT_DATA)
    }

    private fun initUi() {
        with(binding) {
            lifecycleScope.launch {
                setKesehatanAdapter()
                setAtapAdapter()
                setDindingAdapter()
                setLantaiAdapter()
                setPeneranganAdapter()
                setAirAdapter()
                setLuasRumahAdapter()


                btnSimpan.setOnClickListener {
                    /**
                     * getting the input text value
                     */
                    userPreferences.nik.asLiveData().observe(this@InputDataLanjutanActivity, {
                        nik = it
                    })

                    rt = outlinedTextFieldRt.editText?.text.toString().trim()
                    rw = outlinedTextFieldRW.editText?.text.toString().trim()
                    alamat = outlinedTextFieldAlamat.editText?.text.toString().trim()
                    bantuan = outlinedTextFieldBlt.editText?.text.toString().trim()
                    tahap = outlinedTextFieldTahap.editText?.text.toString().trim()

                    kesehatan = outlinedTextFieldKesehatan.editText?.text.toString().trim()
                    atap = outlinedTextFieldAtap.editText?.text.toString().trim()
                    dinding = outlinedTextFieldDinding.editText?.text.toString().trim()
                    lantai = outlinedTextFieldLantai.editText?.text.toString().trim()

                    penerangan = outlinedTextFieldPenerangan.editText?.text.toString().trim()
                    air = outlinedTextFieldAir.editText?.text.toString().trim()
                    luasRumah = outlinedTextFieldLuasRumah.editText?.text.toString().trim()

//                    val data = InputDataBantuan(
//                        nik = nik,
//                        nama = data?.nama,
//                        tgl_lahir = data?.tgl_lahir,
//                        tanggungan = data?.tanggungan,
//                        pendidikan = data?.pendidikan,
//                        profesi = data?.profesi,
//                        status = data?.status,
//                        gaji = data?.gaji,
//                        kota = data?.kota,
//                        kecamatan = data?.kecamatan,
//                        kelurahan = data?.kelurahan,
//                        rt = rt,
//                        rw = rw,
//                        alamat = alamat,
//                        bantuan = bantuan,
//                        tahap = tahap,
//                        kesehatan = kesehatan,
//                        atap = atap,
//                        dinding = dinding,
//                        lantai = lantai,
//                        penerangan = penerangan,
//                        air = air,
//                        luas_rumah = luasRumah
//                    )

//                    Log.d("dataDiLanjutan", "initDataLanjutan: ${data.pendidikan}")

                    nik?.let {
                        inputAssistanceViewModel.inputDataBantuan(
                            nama = data?.nama!!,
                            nik = it,
                            tglLahir = data?.tgl_lahir!!,
                            tanggungan = data?.tanggungan!!,
                            pendidikan = data?.pendidikan!!,
                            profesi = data?.profesi!!,
                            status = data?.status!!,
                            gaji = data?.gaji!!,
                            kota = data?.kota!!,
                            kecamatan = data?.kecamatan!!,
                            kelurahan = data?.kelurahan!!,
                            rt = rt!!,
                            rw = rw!!,
                            alamat = alamat!!,
                            bantuan = bantuan!!,
                            tahap = tahap!!,
                            kesehatan = kesehatan!!,
                            atap = atap!!,
                            dinding = dinding!!,
                            lantai = lantai!!,
                            penerangan = penerangan!!,
                            air = air!!,
                            luasRumah = luasRumah!!
                        )
                    }
                }
            }


        }

    }

    private fun initObservers() {
        with(inputAssistanceViewModel) {
            state.observe(this@InputDataLanjutanActivity, {
                handleLoaderState(it)
            })
            error.observe(this@InputDataLanjutanActivity, {
                handleError(it)
            })
            inputData.observe(this@InputDataLanjutanActivity, {
                handleInputData(it)
            })
        }
    }

    private fun handleInputData(it: InputData) {
        MaterialAlertDialogBuilder(this)
            .setTitle(resources.getString(R.string.title))
            .setMessage(it.status)
            .setNegativeButton("Cancel") { dialog, which ->
                // Respond to negative button press
            }
            .setPositiveButton("Ok") { dialog, which ->
                // Respond to positive button press
                startNewActivityAndClear(HomeActivity::class.java)
            }
            .show()
    }

    private fun handleError(it: String?) {
        it?.let { it1 -> showAlertDialog(it1) }
    }

    private fun handleLoaderState(it: LoaderState) {
        if (it is LoaderState.ShowLoading) {
            binding.loadingView.setVisible()
            setButtonToGrey(true)
        } else {
            binding.loadingView.setGone()
            setButtonToGrey(false)
        }
    }

    private fun setButtonToGrey(state : Boolean) {
        if (state) {
            binding.btnSimpan.setBackgroundColor(resources.getColor(R.color.colorShimmer))
            binding.btnSimpan.setTextColor(resources.getColor(R.color.white))
            binding.btnSimpan.isEnabled = false
        } else {
            binding.btnSimpan.setBackgroundColor(resources.getColor(R.color.bluePrimary))
            binding.btnSimpan.setTextColor(resources.getColor(R.color.white))
            binding.btnSimpan.isEnabled = true
        }
    }

    /**
     * Luas rumah adapter
     */
    private fun setLuasRumahAdapter() {
        val luasRumahAdapter = ArrayAdapter(
            this,
            R.layout.list_items,
            DataDummy.luasRumah
        )
        binding.autoCompleteTextLuasRumah.setAdapter(luasRumahAdapter)
    }

    /**
     * air adapter
     */
    private fun setAirAdapter() {
        val airAdapter = ArrayAdapter(
            this,
            R.layout.list_items,
            DataDummy.jenisAir
        )
        binding.autoCompleteTextAir.setAdapter(airAdapter)
    }

    /**
     * penerangan adapter
     */
    private fun setPeneranganAdapter() {
        val peneranganAdapter = ArrayAdapter(
            this,
            R.layout.list_items,
            DataDummy.penerangan
        )
        binding.autoCompleteTextPenerangan.setAdapter(peneranganAdapter)
    }

    /**
     * Lantai adapter
     */
    private fun setLantaiAdapter() {
        val lantaiAdapter = ArrayAdapter(
            this,
            R.layout.list_items,
            DataDummy.lantai
        )
        binding.autoCompleteTextLantai.setAdapter(lantaiAdapter)
    }

    /**
     * dinding adapter
     */
    private fun setDindingAdapter() {
        val dindingAdapter = ArrayAdapter(
            this,
            R.layout.list_items,
            DataDummy.dinding
        )
        binding.autoCompleteTextDinding.setAdapter(dindingAdapter)
    }

    /**
     * atap adapter
     */
    private fun setAtapAdapter() {
        val atapAdapter = ArrayAdapter(
            this,
            R.layout.list_items,
            DataDummy.atap
        )
        binding.autoCompleteTextAtap.setAdapter(atapAdapter)
    }

    /**
     * kesehatan adapter
     */
    private fun setKesehatanAdapter() {
        val kesehatanAdapter = ArrayAdapter(
            this,
            R.layout.list_items,
            DataDummy.kesehatan
        )
        binding.autoCompleteTextKesehatan.setAdapter(kesehatanAdapter)
    }


    companion object {
        const val INPUT_DATA = "input_date"
    }
}