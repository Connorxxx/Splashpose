package com.connor.splashpose.ui.main

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
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
            CardPhoto(photo)
        }
        lazyPhotoItems.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item { Text(text = "Loading", modifier = Modifier.fillParentMaxSize()) }
                }
                loadState.refresh is LoadState.Error -> {
                    item { Text(text = "Error", modifier = Modifier.fillParentMaxSize()) }
                }
                loadState.append is LoadState.Loading -> {
                    item { Text(text = "Loading") }
                }
                loadState.append is LoadState.Error -> {
                    item { Text(text = "Error") }
                }
            }
        }
    }
}

@Composable
private fun CardPhoto(photo: PhotoItem?) {
    Card(
        elevation = CardDefaults.cardElevation(24.dp),
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, top = 16.dp)
    ) {
        Column {
            Row(Modifier.padding(top = 8.dp, bottom = 8.dp)) {
                AsyncImage(
                    model = photo!!.user!!.profileImage!!.large,
                    contentDescription = "profileImage",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .width(64.dp)
                        .height(48.dp)
                        .padding(start = 16.dp)
                        .clip(CircleShape)
                )
                Text(
                    text = photo.user?.name ?: "null",
                    modifier = Modifier.padding(start = 16.dp),
                    fontSize = 18.sp
                )
            }
            AsyncImage(
                model = photo!!.urls!!.regular,
                contentDescription = "image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }

    }
}