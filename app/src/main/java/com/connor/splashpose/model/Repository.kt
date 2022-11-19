package com.connor.splashpose.model

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.connor.splashpose.model.remote.PhotoPagingSource
import io.ktor.client.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn

class Repository(private val client: HttpClient) {
    fun getPagingData(path: String) = Pager(
        config = PagingConfig(10),
        pagingSourceFactory = { PhotoPagingSource(client, path)}
    ).flow.flowOn(Dispatchers.IO)
}