package com.connor.splashpose.ui.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.connor.splashpose.R
import com.connor.splashpose.constant.IntentString
import com.connor.splashpose.model.remote.PhotoItem
import com.connor.splashpose.ui.theme.SplashposeTheme
import com.connor.splashpose.ui.view.ViewPhoto
import com.connor.splashpose.vm.ViewViewModel
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import org.koin.android.ext.android.inject
import org.koin.androidx.compose.koinViewModel
import java.io.File

class ViewActivity : ComponentActivity() {

    private val id by lazy { intent.getStringExtra(IntentString.PHOTO_ID) ?: IntentString.NULL }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SplashposeTheme {
                val vm = koinViewModel<ViewViewModel>()
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val url by remember { vm.detail(id) }.collectAsState(PhotoItem())
                    ViewPhoto(url.urls!!.regular!!)
                    Text(text = url.urls!!.regular!!)
                }
            }
        }
    }
}