package com.connor.splashpose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import com.connor.splashpose.constant.IntentString
import com.connor.splashpose.model.remote.ApiPath
import com.connor.splashpose.ui.view.ViewActivity
import com.connor.splashpose.ui.main.HomeTopAppBar
import com.connor.splashpose.ui.main.MoreBottomSheet
import com.connor.splashpose.ui.main.PhotoList
import com.connor.splashpose.ui.main.SelectDialog
import com.connor.splashpose.ui.theme.SplashposeTheme
import com.connor.splashpose.utils.startActivity
import com.connor.splashpose.vm.MainViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {

    private val TAG = "MainActivityCompose"

    @OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SplashposeTheme {
                val vm = koinViewModel<MainViewModel>()
                val photos = vm.getPagingData(ApiPath.PHOTOS)
                val scrollBehavior =
                    TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
                val state = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
                var openDialog by remember { mutableStateOf(false) }
                val scope = rememberCoroutineScope()

                Scaffold(
                    modifier = Modifier
                        .nestedScroll(scrollBehavior.nestedScrollConnection),
                    topBar = {
                        HomeTopAppBar(
                            modifier = Modifier,
                            scrollBehavior = scrollBehavior,
                            onMenuClick = {
                                scope.launch {
                                    state.show()
                                }
                            },
                            onSearchClick = {
                                openDialog = true
                            }
                        )
                    }
                ) {
                    PhotoList(modifier = Modifier.padding(it), photos) {
                        startActivity<ViewActivity>(this@MainActivity) {
                            putExtra(IntentString.PHOTO_ID, it)
                        }
                    }
                    MoreBottomSheet(sheetState = state)
                    if (openDialog) {
                        SelectDialog(onDismiss = {
                            openDialog = false
                        }) {
                            openDialog = false
                        }
                    }
                }
            }
        }
    }
}