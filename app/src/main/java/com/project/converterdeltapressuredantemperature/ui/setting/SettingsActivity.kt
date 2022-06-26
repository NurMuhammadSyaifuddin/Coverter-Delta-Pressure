package com.project.converterdeltapressuredantemperature.ui.setting

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.project.converterdeltapressuredantemperature.databinding.ActivitySettingsBinding
import com.project.converterdeltapressuredantemperature.databinding.LayoutEditPressureBinding
import com.project.converterdeltapressuredantemperature.ui.calculate.DeltaPressureActivity
import org.koin.android.viewmodel.ext.android.viewModel

class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding

    private val viewModel: SettingViewodel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onAction()
    }

    private fun onAction() {
        binding.apply {
            btnEditKonstanta.setOnClickListener {
                showAlertDialogEditPressure(this@SettingsActivity){
                    viewModel.savePressure(it.toDouble())
                }.show()
            }
            viewModel.getPressure().observe(this@SettingsActivity){ pressure ->
                val pressureAtm = pressure ?: DeltaPressureActivity.PRESSURE_ATM

                textViewDefaultKonstanta.text = pressureAtm.toString()
            }
        }
    }

    private fun showAlertDialogEditPressure(
        context: Context,
        listenerSave: (String) -> Unit
    ): AlertDialog {
        val binding = LayoutEditPressureBinding.inflate(LayoutInflater.from(context), null, false)
        val dialog = MaterialAlertDialogBuilder(context)
            .setView(binding.root)
            .setCancelable(false)
            .create()

        binding.apply {
            btnCancel.setOnClickListener { dialog.dismiss() }

            if (edtTekananAtmosfer.toString().isBlank()){
                btnSave.isEnabled = false
            }else{
                btnSave.isEnabled = true
                btnSave.setOnClickListener {
                    listenerSave(edtTekananAtmosfer.text.toString())
                    dialog.dismiss()
                }
            }

        }

        return dialog
    }
}