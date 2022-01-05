package com.segunfrancis.manualpaging.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.segunfrancis.manualpaging.data.repository.IManualPagingRepository
import com.segunfrancis.manualpaging.data.repository.ManualPagingRepository
import com.segunfrancis.manualpaging.ui.MainViewModel
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

object AppModule {

    object Api {
        private val dispatcherIO: CoroutineDispatcher by lazy { Dispatchers.IO }
        private val client: HttpClient by lazy {
            ApiClient(Android.create()).client
        }

        private val repository: IManualPagingRepository
            get() {
                return ManualPagingRepository(client = client, dispatcher = dispatcherIO)
            }

        internal val mainViewModelFactory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
                    MainViewModel(repository) as T
                } else {
                    throw IllegalArgumentException("Cannot find viewmodel ${modelClass.canonicalName}")
                }
            }
        }
    }
}
