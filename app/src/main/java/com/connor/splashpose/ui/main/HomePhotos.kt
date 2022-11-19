package com.connor.splashpose.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.AsyncImage
import com.connor.splashpose.model.remote.ApiPath
import com.connor.splashpose.model.remote.PhotoItem
import com.connor.splashpose.vm.MainViewModel
import kotlinx.coroutines.flow.Flow
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomePhotos(modifier: Modifier = Modifier) {
    val vm = koinViewModel<MainViewModel>()
    Column(modifier = modifier) {
        PhotoList(photos = vm.getPagingData(ApiPath.PHOTOS))
    }
}

@Composable
fun PhotoList(photos: Flow<PagingData<PhotoItem>>) {
    val lazyPhotoItems = photos.collectAsLazyPagingItems()
    LazyColumn {
        items(lazyPhotoItems) { photo ->
            AsyncImage(
                model = photo!!.urls!!.regular,
                contentDescription = "image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}