package com.camacho.data.repository

import com.camacho.domain.model.User
import com.camacho.domain.repository.UserRepository

// TODO (Fase 1/2): Implementación con SQLDelight (inyectar TodoDatabase).
class UserRepositoryImpl : UserRepository {
    override fun findByUsername(username: String): User? = TODO("Consultar users por username (User.sq)")
    override fun findById(id: Long): User? = TODO("Consultar users por id")
    override fun create(username: String, passwordHash: String): User = TODO("Insertar usuario y devolverlo")
}
