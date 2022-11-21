package com.connor.splashpose.ui.view

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.alexvasilkov.gestures.views.GestureImageView
import com.connor.splashpose.ui.common.CircularProgress

@Composable
fun ViewPhoto(data: String) {
    val context = LocalContext.current
    val gestureImageView = remember { GestureImageView(context) }
    var success by remember {
        mutableStateOf(false)
    }

    if (!success) {
        CircularProgress(modifier = Modifier
            .fillMaxSize())
    }
    Log.d("ViewPhoto", "ViewPhoto: $data")
    if (data.isNotEmpty()) {
        val img by remember {
            mutableStateOf(data)
        }
        ImageViewer(
            gestureImageView = gestureImageView,
            data = img
        ) {
             success = true
        }
    }

    //AsyncImage(model = data, contentDescription = null)

}