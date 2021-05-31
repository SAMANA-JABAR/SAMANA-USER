package com.aditprayogo.samana_user.presentation.input_assistance

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import com.aditprayogo.core.data.UserPreferences
import com.aditprayogo.core.domain.model.InputDataBantuan
import com.aditprayogo.core.utils.DataDummy
import com.aditprayogo.samana_user.R
import com.aditprayogo.samana_user.databinding.ActivityInputDataBinding
import com.google.android.material.datepicker.MaterialDatePicker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import kotlin.math.log

@AndroidEntryPoint
class InputDataActivity : AppCompatActivity() {

    private val binding: ActivityInputDataBinding by lazy {
        ActivityInputDataBinding.inflate(layoutInflater)
    }

    private var name: String? = null
    private var tanggal: String? = null
    private var tanggungan: String? = null
    private var pendidikan: String? = null
    private var gaji: String? = null
    private var profesi: String? = null
    private var status: String? = null
    private var kota: String? = null
    private var kecamatan: String? = null
    private var kelurahan: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initUi()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun initUi() {
        with(binding) {
            lifecycleScope.launch {
                supportActionBar?.apply {
                    title = "Input Data Bantuan"
                    setDisplayHomeAsUpEnabled(true)
                }

                setTanggalLahirBuilder()
                setTanggunganKeluargaAdapter()
                setPendidikanAdapter()
                setProfesiAdapter()
                setStatusAdapter()
                setGajiAdapter()

                btnNext.setOnClickListener {

                    /**
                     * Get the input text value
                     */
                    name = outlinedTextFieldnama.editText?.text.toString().trim()
                    tanggungan = autoCompleteTextTanggungan.text.toString().trim()
                    pendidikan = autoCompleteTextPendidikan.text.toString().trim()
                    profesi = autoCompleteTextProfesi.text.toString().trim()
                    status = autoCompleteTextStatus.text.toString().trim()
                    gaji = autoCompleteTextGaji.text.toString().trim()

                    kota = outlinedTextFieldKota.editText?.text.toString().trim()
                    kecamatan = outlinedTextFieldKecamatan.editText?.text.toString().trim()
                    kelurahan = outlinedTextFieldKelurahan.editText?.text.toString().trim()

                    val data = InputDataBantuan(
                        nama = name,
                        tanggungan = tanggungan,
                        pendidikan = pendidikan,
                        tgl_lahir = tanggal,
                        profesi = profesi,
                        status = status,
                        kota = kota,
                        gaji = gaji,
                        kecamatan = kecamatan,
                        kelurahan = kelurahan
                    )

                    Log.d("Wakwau", "dataDiInput: $data")

                    startActivity(
                        Intent(
                            this@InputDataActivity,
                            InputDataLanjutanActivity::class.java
                        ).apply {
                            putExtra(InputDataLanjutanActivity.INPUT_DATA, data)
                        })
                }
            }
        }

    }



    /**
     * Date converter
     */
    @SuppressLint("SimpleDateFormat")
    private fun convertDateFormat(date: Long): String {
        val date = Date(date)
        val format = SimpleDateFormat("yyyy.MM.dd")
        val dataFormated = format.format(date)
        return dataFormated
    }

    /**
     * Tanggungan Keluarga adapter
     */
    private fun setTanggunganKeluargaAdapter() {
        val tanggunganKeluargaAdapter = ArrayAdapter(
            this@InputDataActivity,
            R.layout.list_items,
            DataDummy.tanggunganKelarga
        )
        binding.autoCompleteTextTanggungan.setAdapter(tanggunganKeluargaAdapter)
    }

    /**
     * pendidikan adapter
     */
    private fun setPendidikanAdapter() {
        val pendidikanAdapter = ArrayAdapter(
            this@InputDataActivity,
            R.layout.list_items,
            DataDummy.pendidikan
        )
        binding.autoCompleteTextPendidikan.setAdapter(pendidikanAdapter)
    }

    /**
     * Profesi adapter
     */
    private fun setProfesiAdapter() {
        val profesiAdapter = ArrayAdapter(
            this@InputDataActivity,
            R.layout.list_items,
            DataDummy.profesi
        )
        binding.autoCompleteTextProfesi.setAdapter(profesiAdapter)
    }

    /**
     * Gaji adapter
     */
    private fun setGajiAdapter() {
        val gajiAdapter = ArrayAdapter(
            this@InputDataActivity,
            R.layout.list_items,
            DataDummy.gaji
        )
        binding.autoCompleteTextGaji.setAdapter(gajiAdapter)
    }

    /**
     * Profesi adapter
     */
    private fun setStatusAdapter() {
        val statusAdapter = ArrayAdapter(
            this@InputDataActivity,
            R.layout.list_items,
            DataDummy.status
        )
        binding.autoCompleteTextStatus.setAdapter(statusAdapter)
    }

    /**
     * Tanggal lahir
     */
    private fun setTanggalLahirBuilder() {
        //tanggal lahir
        binding.btnDate.setOnClickListener {
            val datePicker =
                MaterialDatePicker.Builder.datePicker()
                    .setTitleText("Select date")
                    .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                    .build()

            datePicker.show(supportFragmentManager, "MyTag")
            datePicker.addOnPositiveButtonClickListener {
                val data = convertDateFormat(it)
                tanggal = data
                binding.etDate.setText(tanggal)
            }
        }
    }

}