package com.connor.splashpose.ui.view

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.alexvasilkov.gestures.views.GestureImageView
import com.connor.splashpose.model.remote.PhotoItem
import com.connor.splashpose.ui.common.CircularProgress

@Composable
fun ViewPhoto(photo: PhotoItem) {
    val context = LocalContext.current
    val gestureImageView = remember { GestureImageView(context) }
    var success by remember { mutableStateOf(false) }

    if (!success) {
        CircularProgress(modifier = Modifier
            .fillMaxSize())
    }
    var img by remember {
        mutableStateOf("")
    }
    Column {
        if (img.isNotEmpty()) {
            ImageViewer(
                gestureImageView = gestureImageView,
                data = img
            ) {
                success = true
            }
        }
    }

    LaunchedEffect(photo) {
        img = photo.urls!!.full!!
    }
}