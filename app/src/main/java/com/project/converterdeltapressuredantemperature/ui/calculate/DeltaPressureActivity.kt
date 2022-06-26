package com.project.converterdeltapressuredantemperature.ui.calculate

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import com.project.converterdeltapressuredantemperature.R
import com.project.converterdeltapressuredantemperature.databinding.ActivityDeltaPressureBinding
import org.koin.android.viewmodel.ext.android.viewModel

class DeltaPressureActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDeltaPressureBinding

    private val viewModel: CalculateViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeltaPressureBinding.inflate(layoutInflater)
        setContentView(binding.root)

        calculateDeltaPressure()

        onAction()
    }

    private fun onAction() {
        binding.apply {
            edtPressureEvc.addTextChangedListener {
                if (it.toString().isNotBlank()) textInputPressureEvc.error = null
            }
            edtPressureGauge.addTextChangedListener {
                if (it.toString().isNotBlank()) textInputPressureGauge.error = null
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun calculateDeltaPressure() {
        binding.apply {
            viewModel.getPressure().observe(this@DeltaPressureActivity) { pressure ->
                val pressureAtm = pressure ?: PRESSURE_ATM

                btnResult.setOnClickListener {
                    val pressureEvc = edtPressureEvc.text.toString()
                    val pressureGauge = edtPressureGauge.text.toString()

                    textInputPressureEvc.error =
                        if (pressureEvc.isBlank()) getString(R.string.field_cannot_empty) else null
                    textInputPressureGauge.error =
                        if (pressureGauge.isBlank()) getString(R.string.field_cannot_empty) else null

                    if (fieldIsValid(pressureEvc, pressureGauge)) {
                        val deltaPressure =
                            ((pressureEvc.toDouble() - pressureAtm) - pressureGauge.toDouble()) / (pressureEvc.toDouble() - pressureAtm)
                        val deltaPressurePersen = "%.2f".format(deltaPressure * 100)

                        val deltaPressureFormatDecimal = "%.4f".format(deltaPressure)

                        tvResult.text = "$deltaPressureFormatDecimal\natau\n$deltaPressurePersen%"
                    }

                }

            }
        }
    }

    private fun fieldIsValid(pressureEvc: String, pressureGauge: String): Boolean =
        !(pressureEvc.isBlank() || pressureGauge.isBlank())

    companion object {
        const val PRESSURE_ATM: Double = 1.01325
    }
}