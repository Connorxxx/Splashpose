package com.connor.splashpose.model

import com.connor.splashpose.model.remote.ApiPath
import com.connor.splashpose.model.remote.PhotoItem
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

class ViewRepository(private val client: HttpClient) {

    suspend fun detail(id: String) = client.get {
        url { path(ApiPath.PHOTOS, id) }
    }.body<PhotoItem>()
}