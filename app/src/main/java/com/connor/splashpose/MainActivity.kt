package com.connor.splashpose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.connor.splashpose.constant.IntentString
import com.connor.splashpose.intent.onClickMainImage
import com.connor.splashpose.ui.ViewActivity
import com.connor.splashpose.ui.main.HomePhotos
import com.connor.splashpose.ui.main.HomeTopAppBar
import com.connor.splashpose.ui.theme.SplashposeTheme
import com.connor.splashpose.utils.startActivity
import com.connor.splashpose.vm.MainViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {

    private val TAG = "MainActivityCompose"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SplashposeTheme {
                val vm = koinViewModel<MainViewModel>()
                event(vm)
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column {
                        HomeTopAppBar()
                        HomePhotos(modifier = Modifier.fillMaxSize(), vm)
                    }

                }
            }
        }
    }
    private fun event(vm: MainViewModel) {
        lifecycleScope.launchWhenCreated {
            vm.event.collect {
                Log.d(TAG, "event: ")
                with(it) {
                    onClickMainImage {
                        startActivity<ViewActivity>(this@MainActivity) {
                            putExtra(IntentString.PHOTO_ID, it)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SplashposeTheme {
        Greeting("Android")
    }
}