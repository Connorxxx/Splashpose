package com.connor.splashpose.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.connor.splashpose.intent.Event
import com.connor.splashpose.model.Repository
import io.ktor.client.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository, val client: HttpClient) : ViewModel() {

    private val _event = MutableSharedFlow<Event>()
    val event = _event.asSharedFlow()

    private val _state = MutableStateFlow("")
    val state = _state.asStateFlow()

    fun sendEvent(event: Event) {
        viewModelScope.launch {
            _event.emit(event)
        }
    }

    fun sendState(state: String)  {
        _state.value = state
    }


    fun getPagingData(path: String) =
        repository.getPagingData(path).flowOn(Dispatchers.Default).cachedIn(viewModelScope)
}