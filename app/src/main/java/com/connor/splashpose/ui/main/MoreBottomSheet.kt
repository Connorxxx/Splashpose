package com.connor.splashpose.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MoreBottomSheet(
    modifier: Modifier = Modifier, sheetState: ModalBottomSheetState
) {
    ModalBottomSheetLayout(
        modifier = modifier,
        sheetState = sheetState,
        sheetContent = {
            Row(Modifier.height(100.dp).padding(34.dp)) {
                Icon(
                    Icons.Default.Favorite,
                    contentDescription = "Localized description"
                )
                Text(text = "test")
            }
        },
        sheetShape = RoundedCornerShape(12.dp)
    ) {

    }
}