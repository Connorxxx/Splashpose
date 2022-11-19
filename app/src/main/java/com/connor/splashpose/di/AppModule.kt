package com.connor.splashpose.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.connor.splashpose.ApiKey
import com.connor.splashpose.BuildConfig
import com.connor.splashpose.model.Repository
import com.connor.splashpose.vm.MainViewModel
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.compression.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import okhttp3.Cache
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import java.util.concurrent.TimeUnit

val appModule = module {
    viewModel { MainViewModel(get()) }
    single { Repository(get()) }
    single { client(get()) }
}

fun client(context: Context) = HttpClient(OkHttp) {
    defaultRequest {
        url {
            protocol = URLProtocol.HTTPS
            host = "api.unsplash.com"
            parameters.append(ApiKey.CLIENT_ID, ApiKey.ACCESS_KEY)
        }
    }
    engine {
        threadsCount = 16
        clientCacheSize = 1024 * 1204 * 128
        config {
            connectTimeout(30, TimeUnit.SECONDS)
            readTimeout(30, TimeUnit.SECONDS)
            writeTimeout(30, TimeUnit.SECONDS)
            cache(Cache(context.cacheDir, 1024 * 1204 * 128))
            if (BuildConfig.DEBUG) {
                addInterceptor(
                    ChuckerInterceptor.Builder(context)
                        .collector(ChuckerCollector(context))
                        .maxContentLength(250000L)
                        .redactHeaders(emptySet())
                        .alwaysReadResponseBody(false)
                        .build()
                )
            }
        }
    }
    install(ContentEncoding) {
        deflate(1.0F)
        gzip(0.9F)
    }
    install(ContentNegotiation) {
        json(Json {
            ignoreUnknownKeys = true
            coerceInputValues = true
            prettyPrint = true
            isLenient = true
        })
    }
}