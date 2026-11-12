package com.camacho.domain.model

// Entidad de dominio Tarea.
data class Task(
    val id: Long,
    val userId: Long,
    val title: String,
    val description: String?,
    val completed: Boolean,
    val createdAt: Long
)
