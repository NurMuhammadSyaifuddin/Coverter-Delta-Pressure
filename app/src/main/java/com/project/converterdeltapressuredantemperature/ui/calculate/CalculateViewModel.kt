package com.project.converterdeltapressuredantemperature.ui.calculate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.project.converterdeltapressuredantemperature.preference.PressurePreerence

class CalculateViewModel(private val pref: PressurePreerence): ViewModel() {

    fun getPressure() = pref.getPressure().asLiveData()

}