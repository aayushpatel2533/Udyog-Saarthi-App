package com.example.udyogsaarthi.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _items = MutableLiveData<List<String>>()
    val items: LiveData<List<String>> = _items

    init {
        loadItems()
    }

    private fun loadItems() {
        // TODO: Replace with real data from repository
        _items.value = listOf("Item 1", "Item 2", "Item 3")
    }
}
