package com.project.converterdeltapressuredantemperature.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.project.converterdeltapressuredantemperature.databinding.ActivityMainBinding
import com.project.converterdeltapressuredantemperature.ui.calculate.DeltaPressureActivity
import com.project.converterdeltapressuredantemperature.ui.calculate.DeltaTemperatureActivity
import com.project.converterdeltapressuredantemperature.ui.setting.SettingsActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onAction()
    }

    private fun onAction() {
        binding.apply {
            btnDeltaPressure.setOnClickListener {
                Intent(this@MainActivity, DeltaPressureActivity::class.java).also { intent ->
                    startActivity(intent)
                }
            }
            btnDeltaTemperature.setOnClickListener {
                Intent(this@MainActivity, DeltaTemperatureActivity::class.java).also { intent ->
                    startActivity(intent)
                }
            }
            imgSetting.setOnClickListener {
                Intent(this@MainActivity, SettingsActivity::class.java).also { intent ->
                    startActivity(intent)
                }
            }
        }
    }
}