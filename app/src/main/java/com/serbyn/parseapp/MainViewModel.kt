package com.serbyn.parseapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _uiState = MutableStateFlow("")
    val uiState = _uiState.asStateFlow()

    private val trie = Trie()

    val exampleOfDictionary = listOf(
        "cat",
        "huge",
        "dog",
        "doggy",
        "run",
        "running",
        "runnable",
        "second",
        "secondary"
    )

    init {
        exampleOfDictionary.map { trie.insert(it) }
    }

    fun parseString(innerValue: String) {
        viewModelScope.launch(Dispatchers.Default) {
            val result = trie.splitString(innerValue)
            _uiState.emit(result)
        }
    }
}