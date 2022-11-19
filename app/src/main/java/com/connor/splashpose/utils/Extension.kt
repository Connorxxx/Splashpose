package com.connor.splashpose.utils

import android.content.Context
import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.paging.PagingSource.LoadResult
import coil.load
import coil.network.HttpException
import com.connor.splashpose.App
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import java.io.IOException
import kotlin.math.floor

inline fun <reified T : Any> fire(block: () -> LoadResult<Int, T>) = try {
    block()
} catch (e: IOException) {
    LoadResult.Error(e)
} catch (e: HttpException) {
    LoadResult.Error(e)
}

inline fun <reified T> startActivity(context: Context, block: Intent.() -> Unit) {
    val intent = Intent(context, T::class.java)
    intent.block()
    context.startActivity(intent)
}

fun EditText.textChanges() = callbackFlow {
    val listener = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            p0?.let { trySend(it) }
        }

        override fun afterTextChanged(p0: Editable?) {

        }
    }
    addTextChangedListener(listener)
    awaitClose {
        removeTextChangedListener(listener)
    }
}

fun ImageView.loadWithQuality(
    highQuality: String,
    lowQuality: String,
    placeholderRes: Int? = null,
    errorRes: Int? = null,
) {
    load(lowQuality) {
        placeholderRes?.let {
            placeholder(placeholderRes)
        }
        listener(onSuccess = { _, _ ->
            load(highQuality) {
                placeholder(drawable) // If there was a way to not clear existing image before loading, this would not be required
                errorRes?.let { error(errorRes) }
            }
        })
    }
}

fun Double.toScore() = this.times(10).toInt()

fun Int.toHours() = floor((this / 60).toDouble()).toInt()

fun String.showToast() {
    Toast.makeText(App.context, this, Toast.LENGTH_LONG).show()
}