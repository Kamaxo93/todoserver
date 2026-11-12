package com.camacho.domain.repository

// Contrato de gestión de sesiones (tokens).
interface SessionRepository {
    fun create(userId: Long): String
    fun findUserIdByToken(token: String): Long?
    fun delete(token: String)
}
