package com.project.converterdeltapressuredantemperature.ui.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.project.converterdeltapressuredantemperature.preference.PressurePreerence
import kotlinx.coroutines.launch

class SettingViewodel(private val pref: PressurePreerence): ViewModel() {

    fun savePressure(value: Double){
        viewModelScope.launch {
            pref.savePressure(value)
        }
    }

    fun getPressure() = pref.getPressure().asLiveData()

}