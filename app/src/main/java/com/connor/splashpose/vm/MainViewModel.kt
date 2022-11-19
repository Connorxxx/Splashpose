package com.connor.splashpose.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.connor.splashpose.model.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn

class MainViewModel(private val repository: Repository) : ViewModel() {

    fun getPagingData(path: String) =
        repository.getPagingData(path).flowOn(Dispatchers.Default).cachedIn(viewModelScope)
}