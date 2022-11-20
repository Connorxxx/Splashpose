package com.connor.splashpose.ui.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.request.ImageRequest
import com.alexvasilkov.gestures.views.GestureImageView
import com.connor.splashpose.ui.common.CircularProgress

@Composable
fun ViewPhoto() {
    val context = LocalContext.current
    val gestureImageView = remember { GestureImageView(context) }
    var success by remember {
        mutableStateOf(false)
    }

    if (!success) {
        CircularProgress(modifier = Modifier
            .fillMaxSize())
    }
        ImageViewer(
            gestureImageView = gestureImageView,
            data = "https://images.unsplash.com/photo-1664575198263-269a022d6e14?crop=entropy&cs=tinysrgb&fm=jpg&ixid=MnwxNzA0NDd8MXwxfGFsbHwxMXx8fHx8fDJ8fDE2Njg5MTIwNDY&ixlib=rb-4.0.3&q=80"
        ) {
            success = true
        }

}