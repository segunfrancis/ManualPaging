package com.segunfrancis.manualpaging.data.repository

import com.segunfrancis.manualpaging.data.model.MovieBaseResponse

interface IManualPagingRepository {
    suspend fun getMovies(page: Int): MovieBaseResponse
}
