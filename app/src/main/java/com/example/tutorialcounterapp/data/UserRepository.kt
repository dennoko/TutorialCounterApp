package com.example.tutorialcounterapp.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
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
}