package com.project.converterdeltapressuredantemperature.preference

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PressurePreerence(private val dataStore: DataStore<Preferences>) {

    private val pressureKey = doublePreferencesKey(PRESSURE_KEY)

    suspend fun savePressure(value: Double){
        dataStore.edit {
            it[pressureKey] = value
        }
    }

    fun getPressure(): Flow<Double?> =
        dataStore.data.map {
            it[pressureKey]
        }

    companion object{
        private const val PRESSURE_KEY = "pressure_keu"
    }
}