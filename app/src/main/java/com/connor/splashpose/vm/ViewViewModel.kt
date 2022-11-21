package com.connor.splashpose.vm

import androidx.lifecycle.ViewModel
import com.connor.splashpose.model.ViewRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class ViewViewModel(private val repository: ViewRepository) : ViewModel() {

    fun detail(id: String) = flow {
        emit(repository.detail(id))
    }.flowOn(Dispatchers.IO)
}