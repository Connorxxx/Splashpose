package com.connor.splashpose.ui.main

import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.connor.splashpose.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopAppBar(
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior,
    onMenuClick: () -> Unit,
    onSearchClick: () -> Unit
) {
    Card(
        modifier = modifier,
        elevation = 4.dp,
        shape = RectangleShape,
    ) {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = stringResource(R.string.app_name),
                    fontWeight = FontWeight.ExtraLight,
                    fontSize = 30.sp
                )
            },
            scrollBehavior = scrollBehavior,
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = Color.White
            ),
            navigationIcon = {
                IconButton(onClick = onMenuClick) {
                    Icon(Icons.Filled.Menu, null)
                }
            },
            actions = {
                IconButton(
                    onClick = onSearchClick //do something
                ) {
                    Icon(Icons.Filled.Search, null)
                }
            }
        )
    }

}