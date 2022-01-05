package com.segunfrancis.manualpaging.data.model

import kotlinx.serialization.Serializable

@Serializable
data class MovieBaseResponse(
    val dates: Dates,
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)
