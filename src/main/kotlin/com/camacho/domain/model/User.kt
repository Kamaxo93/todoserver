package com.camacho.domain.model

// Entidad de dominio (pura, sin dependencias de framework ni BD).
data class User(
    val id: Long,
    val username: String,
    val passwordHash: String,
    val createdAt: Long
)
