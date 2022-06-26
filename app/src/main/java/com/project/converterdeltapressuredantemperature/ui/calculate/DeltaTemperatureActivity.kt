package com.project.converterdeltapressuredantemperature.ui.calculate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import com.project.converterdeltapressuredantemperature.R
import com.project.converterdeltapressuredantemperature.databinding.ActivityDeltaTemperatureBinding

class DeltaTemperatureActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDeltaTemperatureBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeltaTemperatureBinding.inflate(layoutInflater)
        setContentView(binding.root)

        calculateDeltaTemperature()

        onAction()
    }

    private fun onAction() {
        binding.apply {
            edtTemperatureEvc.addTextChangedListener {
                if (it.toString().isNotBlank()) textInputTemperatureEvc.error = null
            }
            edtTemperatureGauge.addTextChangedListener {
                if (it.toString().isNotBlank()) textInputTemperatureGauge.error = null
            }
        }
    }

    private fun calculateDeltaTemperature() {
        binding.apply {
            btnResult.setOnClickListener {
                val temperatureEvc = edtTemperatureEvc.text.toString()
                val temperatureGauge = edtTemperatureGauge.text.toString()

                if (fieldIsValid(temperatureEvc, temperatureGauge)) {
                    val deltaTemperature = temperatureEvc.toDouble() - temperatureGauge.toDouble()

                    tvResult.text = deltaTemperature.toString()
                }

                textInputTemperatureEvc.error =
                    if (temperatureEvc.isBlank()) getString(R.string.field_cannot_empty) else null
                textInputTemperatureGauge.error =
                    if (temperatureGauge.isBlank()) getString(R.string.field_cannot_empty) else null

            }
        }
    }

    private fun fieldIsValid(temperatureEvc: String, temperatureGauge: String): Boolean =
        !(temperatureEvc.isBlank() || temperatureGauge.isBlank())
}