package com.dariusz.compactweather.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.collect

class PreferencesUtils(private val context: Context) {

    private val Context._dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
    var dataStore = context._dataStore

    private val KEY_PREF = intPreferencesKey("key")

    suspend fun insertNewKeyIntoDataStore(newKey: Int){
        context._dataStore.edit { settings ->
            settings[KEY_PREF] = newKey
        }
    }

    suspend fun readKeyFromDataStore() =
        context._dataStore.data.collect { preferences ->
            preferences[KEY_PREF]
        }


}