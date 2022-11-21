package com.connor.splashpose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import com.connor.splashpose.constant.IntentString
import com.connor.splashpose.model.remote.ApiPath
import com.connor.splashpose.ui.view.ViewActivity
import com.connor.splashpose.ui.main.HomeTopAppBar
import com.connor.splashpose.ui.main.PhotoList
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
            }
        }
    }
}