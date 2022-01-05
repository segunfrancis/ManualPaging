package com.segunfrancis.manualpaging.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Dates(
    val maximum: String,
    val minimum: String
)
