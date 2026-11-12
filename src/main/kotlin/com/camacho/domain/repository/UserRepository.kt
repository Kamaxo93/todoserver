package com.camacho.domain.repository

import com.camacho.domain.model.User

// Contrato de acceso a datos de usuarios (implementación en la capa data).
interface UserRepository {
    fun findByUsername(username: String): User?
    fun findById(id: Long): User?
    fun create(username: String, passwordHash: String): User
}
