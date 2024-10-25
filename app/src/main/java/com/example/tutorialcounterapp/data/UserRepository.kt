package com.example.tutorialcounterapp.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserRepository(private val dataStore: DataStore<Preferences>) {

    private object PreferenceKeys {
        val ITEMS = stringSetPreferencesKey("items")
    }

    val currentItems: Flow<Set<String>> =
        dataStore.data.map { preferences ->
            preferences[PreferenceKeys.ITEMS] ?: emptySet()
        }

    suspend fun saveItems(items: Set<String>){
        dataStore.edit{ preferences ->
            preferences[PreferenceKeys.ITEMS] = items
        }
    }

    suspend fun saveCountValue(itemName: String, countValue: Int){
        val COUNTVALUE = intPreferencesKey(itemName)
        dataStore.edit { preferences ->
            preferences[COUNTVALUE] = countValue
        }
    }

    suspend fun loadCountValue(itemName: String): Flow<Int> {
        val COUNTVALUE = intPreferencesKey(itemName)
        val countValue: Flow<Int> =
            dataStore.data.map { preferences ->
                preferences[COUNTVALUE] ?:0
            }
        return countValue
    }
}