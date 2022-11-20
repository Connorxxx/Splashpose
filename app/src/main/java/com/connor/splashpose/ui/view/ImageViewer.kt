package com.connor.splashpose.ui.view

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import coil.load
import com.alexvasilkov.gestures.views.GestureImageView

@Composable
fun ImageViewer(
    modifier: Modifier = Modifier,
    gestureImageView: GestureImageView,
    data: Any?,
    block: () -> Unit
) {
    AndroidView(
        factory = {
            gestureImageView.apply {
                controller.settings
                    .setMaxZoom(3f)
                    .setOverzoomFactor(4f)
                    .doubleTapZoom = 0.97f
                load(data) {
                    listener(onSuccess = { _, _ ->
                        block()
                    })
                }
            }
        },
        modifier = modifier.fillMaxWidth()
    )
}