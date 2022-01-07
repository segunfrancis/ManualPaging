package com.segunfrancis.manualpaging.data.repository

import com.segunfrancis.manualpaging.BuildConfig
import com.segunfrancis.manualpaging.data.model.MovieBaseResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ManualPagingRepository @Inject constructor(
    private val client: HttpClient,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : IManualPagingRepository {
    override suspend fun getMovies(page: Int): MovieBaseResponse {
        return withContext(dispatcher) {
            client.get("movie/now_playing") {
                parameter("api_key", BuildConfig.API_KEY)
                parameter("page", page)
            }
        }
    }
}
