package com.example.tutorialcounterapp.counter_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.tutorialcounterapp.MyApplication
import com.example.tutorialcounterapp.data.UserRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CounterScreenViewModel(private val userRepository: UserRepository): ViewModel() {

    val uiState: StateFlow<CounterScreenUiState> =
        userRepository.currentItems.map { itemNameSet ->
            CounterScreenUiState(itemNameSet)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = CounterScreenUiState()
        )

    fun saveItems(items: Set<String>){
        viewModelScope.launch {
            userRepository.saveItems(items)
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as MyApplication)
                CounterScreenViewModel(application.userRepository)
            }
        }
    }

    // TODO: fun saveCountValue(itemName: String, countValue: Int) の実装(アイテム名をキーとしてカウント値を保存する)


    // TODO: fun loadCountValue(itemName: String): Int の実装(アイテム名をキーとして保存されたカウント値を取得する)


    // TODO: fun renameItem(previousItemName: String, newItemName: String) の実装(アイテム名を変更する)
    // dennoko 担当
}