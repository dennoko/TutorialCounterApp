package com.example.tutorialcounterapp.counter_screen

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.tutorialcounterapp.data.UserRepository

data class CounterScreenUiState(
    val itemNameSet: Set<String> = emptySet(),
)

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = "setting"
)

class MyApplication: Application() {
    lateinit var userRepository: UserRepository
    override fun onCreate() {
        super.onCreate()
        userRepository = UserRepository(dataStore)
    }
}
