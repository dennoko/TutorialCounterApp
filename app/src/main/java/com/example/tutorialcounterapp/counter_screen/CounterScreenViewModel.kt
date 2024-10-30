package com.example.tutorialcounterapp.counter_screen

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.tutorialcounterapp.data.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf
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

    // TODO: fun saveItems(items: Set<String>) の実装(アイテム名のリストを保存する)

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

    fun saveCountValue(itemName: String, countValue: Int){
        viewModelScope.launch {
            userRepository.saveCountValue(itemName,countValue)
        }
    }

    // TODO: fun loadCountValue(itemName: String): Int の実装(アイテム名をキーとして保存されたカウント値を取得する)

    fun loadCountValue(itemName: String): Flow<Int>{
        var countValue: Flow<Int> = flowOf(0)
        viewModelScope.launch {
            countValue = userRepository.loadCountValue(itemName)
        }
        return countValue
    }

    fun renameItem(itemName: String, newItemName: String){
        viewModelScope.launch {
            userRepository.renameItem(itemName, newItemName)
        }
    }

    fun addItem(itemName: String) {
        viewModelScope.launch {
            if(itemName != "") {
                val newItemSet = uiState.value.itemNameSet + itemName
                saveItems(newItemSet)
            }
        }
    }

    fun deleteItem(itemName: String){
        viewModelScope.launch {
            val newItemSet = uiState.value.itemNameSet - itemName
            saveItems(newItemSet)
        }
    }


}