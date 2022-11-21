package com.connor.splashpose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.connor.splashpose.constant.IntentString
import com.connor.splashpose.intent.onClickMainImage
import com.connor.splashpose.model.remote.ApiPath
import com.connor.splashpose.ui.ViewActivity
import com.connor.splashpose.ui.main.HomeTopAppBar
import com.connor.splashpose.ui.main.PhotoList
import com.connor.splashpose.ui.theme.SplashposeTheme
import com.connor.splashpose.utils.startActivity
import com.connor.splashpose.vm.MainViewModel
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {

    private val TAG = "MainActivityCompose"

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                val vm = koinViewModel<MainViewModel>()
                val photos = vm.getPagingData(ApiPath.PHOTOS)
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colors.background
//                ) {
                    val scrollBehavior =
                        TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
                    Scaffold(
                        modifier = Modifier
                            .nestedScroll(scrollBehavior.nestedScrollConnection),
                        topBar = { HomeTopAppBar(scrollBehavior = scrollBehavior) }
                    ) {
                        PhotoList(modifier = Modifier.padding(it), photos) {
                            startActivity<ViewActivity>(this@MainActivity) {
                                putExtra(IntentString.PHOTO_ID, it)
                            }
                        }
                    }
               // }
            }
        }
    }
}