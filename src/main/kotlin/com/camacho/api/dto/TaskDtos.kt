package com.camacho.api.dto

import kotlinx.serialization.Serializable

// DTOs de la API de tareas.
@Serializable
data class TaskRequest(
    val title: String,
    val description: String? = null,
    val completed: Boolean = false
)

@Serializable
data class TaskResponse(
    val id: Long,
    val title: String,
    val description: String?,
    val completed: Boolean,
    val createdAt: Long
)
