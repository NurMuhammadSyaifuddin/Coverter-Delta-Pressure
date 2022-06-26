package com.project.converterdeltapressuredantemperature.ui.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.project.converterdeltapressuredantemperature.preference.PressurePreerence
import com.project.converterdeltapressuredantemperature.ui.calculate.CalculateViewModel
import com.project.converterdeltapressuredantemperature.ui.setting.SettingViewodel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { CalculateViewModel(get()) }
    viewModel { SettingViewodel(get()) }
}

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "pressure")

val preferenceModule = module {
    factory { get<Context>().dataStore }
    single { PressurePreerence(get()) }
}