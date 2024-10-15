package com.example.tutorialcounterapp

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.tutorialcounterapp.data.UserRepository

class MyApplication: Application() {
    lateinit var userRepository: UserRepository
    override fun onCreate() {
        super.onCreate()
        userRepository = UserRepository(dataStore)
    }
}

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = "setting"
)
