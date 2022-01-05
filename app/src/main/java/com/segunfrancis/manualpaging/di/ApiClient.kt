package com.segunfrancis.manualpaging.di

import com.segunfrancis.manualpaging.util.AppConstants
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.features.HttpTimeout
import io.ktor.client.features.defaultRequest
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.ANDROID
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.client.request.host
import io.ktor.http.URLProtocol
import kotlinx.serialization.json.Json

class ApiClient(engine: HttpClientEngine) {
    val client: HttpClient by lazy {
        HttpClient(engine) {
            defaultRequest {
                host = AppConstants.BASE_URL
                url {
                    protocol = URLProtocol.HTTPS
                }
            }
            install(JsonFeature) {
                serializer = KotlinxSerializer(Json {
                    prettyPrint = true
                    isLenient = true
                })
            }
            install(HttpTimeout) {
                requestTimeoutMillis = AppConstants.NETWORK_TIMEOUT
                connectTimeoutMillis = AppConstants.NETWORK_TIMEOUT
            }
            install(Logging) {
                logger = Logger.ANDROID
                level = LogLevel.BODY
            }
        }
    }
}
