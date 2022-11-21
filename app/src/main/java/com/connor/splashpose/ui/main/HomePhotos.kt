package com.connor.splashpose.ui.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
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
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.connor.splashpose.model.remote.PhotoItem
import com.connor.splashpose.ui.common.CircularProgress
import kotlinx.coroutines.flow.Flow

@Composable
fun PhotoList(
    modifier: Modifier = Modifier,
    photos: Flow<PagingData<PhotoItem>>,
    onImgCLick: (String) -> Unit
) {
    val lazyPhotoItems = photos.collectAsLazyPagingItems()
    LazyColumn(modifier = modifier) {
        items(lazyPhotoItems) { photo ->
            CardPhoto(photo!!, onImgCLick)
        }
        lazyPhotoItems.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item {
                        CircularProgress(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(top = 32.dp)
                        )
                    }
                }
                loadState.refresh is LoadState.Error -> {
                    item { Text(text = "Error", modifier = Modifier.fillParentMaxSize()) }
                }
                loadState.append is LoadState.Loading -> {
                    item { CircularProgress(modifier = Modifier.fillMaxSize()) }
                }
                loadState.append is LoadState.Error -> {
                    item { Text(text = "Error") }
                }
            }
        }
    }
}

@Composable
private fun CardPhoto(
    photo: PhotoItem,
    onImgCLick: (String) -> Unit
) {
    Card(
        elevation = CardDefaults.cardElevation(24.dp),
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, top = 16.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                Modifier.padding(top = 8.dp, bottom = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                AsyncImage(
                    model = photo.user!!.profileImage!!.large,
                    contentDescription = "profileImage",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .width(64.dp)
                        .height(48.dp)
                        .padding(start = 16.dp)
                        .clip(CircleShape)
                )
                Text(
                    text = photo.user.name ?: "null",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp),
                    fontSize = 20.sp
                )
            }
            SubcomposeAsyncImage(
                model = photo.urls!!.regular,
                contentDescription = "image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .clickable(onClick = { onImgCLick(photo.id!!) }),
            ) {
                val state = painter.state
                if (state is AsyncImagePainter.State.Loading) {
                    CircularProgress(
                        modifier = Modifier
                            .height(400.dp)
                            .fillMaxWidth()
                    )
                } else {
                    SubcomposeAsyncImageContent()
                }
            }
        }
    }
}
